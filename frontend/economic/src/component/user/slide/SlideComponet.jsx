import React, { useEffect, useState } from 'react';
import './style.css';
import banner0 from "/src/assets/images/neuvillette.jpg";
import banner1 from "/src/assets/images/renheng.jpg";
import banner2 from "/src/assets/images/gepard.jpg";

const SlideComponet = () => {

    const [currentIndex, setCurrentIndex] = useState(0);

    //useEffect thực hiện các side effect trong functional component
    /**'Side effect' có thể là các thao tác như 
    - gọi API
    - tương tác với DOM
    - thiết lập subscription
    - ...*/
    // hàm truyền vào useEffect sẽ được gọi sau mỗi lần render
    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentIndex(prevIndex => (prevIndex +1) % 3); //// Thay 3 bằng số lượng slide trong carousel của bạn
        }, 3000); //thời gian chờ giữa các slide

        return () => clearInterval(interval); //xóa interval khi component unmout
    }, []); // danh sách biến phụ thuộc [] là một mảng, nếu biến nào đó trong mảng thay đổi giữa các lần render, hàm side effect sẽ được gọi lại

    const goToPreviousSlide = () => {
        setCurrentIndex(prevIndex => (prevIndex === 0 ? 2 : prevIndex -1));
    }

    const goToNextSlide = () => {
        setCurrentIndex(prevIndex => (prevIndex +1) % 3);
    }

    return (
        <div className="container">
            <div className="carousel">
                <div className="slides">
                    <div className="slides-item"
                         style={{ display: currentIndex === 0 ? 'block' : 'none' }}>
                        <img src={banner0} alt='banner' />
                    </div>
                    <div className="slides-item"
                         style={{ display: currentIndex === 1 ? 'block' : 'none' }}>
                        <img src={banner1} alt='banner' />
                    </div>
                    <div className="slides-item"
                         style={{ display: currentIndex === 2 ? 'block' : 'none' }}>
                        <img src={banner2} alt='banner' />
                    </div>
                </div>
                <button className="carousel-control-prev" type="button" onClick={goToPreviousSlide}>
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" onClick={goToNextSlide}>
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    )
}

export default SlideComponet
