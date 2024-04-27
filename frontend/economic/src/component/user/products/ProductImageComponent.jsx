import React, { useEffect, useState } from 'react';
import './style.css';
const ProductImageComponent = ({ productImages }) => {

    //nếu productImages tồn tại ít nhất mội phần tử, currentImage sẽ được khởi tạo với hình ảnh đầu tiên
    const [currentImage, setCurrentImage] = useState('');
    const [showPopup, setShowPopup] = useState(false); //Stata để kiểm soát hiển thị của popup

    const openPopup = () => {
        setShowPopup(true);
    }
    const closePopup = () => {
        setShowPopup(false);
    }

    useEffect(() => {
        //kiểm tra xem productImages có phần tử nào không
        if (productImages && productImages.length > 0) {
            //nếu có, cập nhật currentImage với hình ảnh đầu tiên
            setCurrentImage(productImages[0].imageUrl);
        } else {
            setCurrentImage('');
        }
    }, [productImages]); //chạy lại mỗi khi productImages thay đổi

    return (
        <div>
            {
                showPopup && (
                    <div className="popup-product">
                        <div className="popup-inner">
                            <img src={currentImage} alt='current' className='popup-image'/>
                            <button onClick={closePopup}>X</button>
                        </div>
                    </div>
                )
            }

            {
                currentImage ? (
                    <img src={currentImage} alt='current' className='large-image' onClick={openPopup}/>
                ) : (
                    <p>No image available</p> // Hiển thị thông báo khi không có hình ảnh
                )
            }

            <div className='thumbnail-container'>
                {Array.isArray(productImages) && productImages.map((image, index) => (
                    <img
                        key={index}
                        className='product-thumbnail'
                        src={image.imageUrl}
                        alt={`Image ${index + 1}`}
                        onMouseOver={() => setCurrentImage(image.imageUrl)} />//khi di chuột qua mỗi hình ảnh nhỏ (onMouseOver), setCurrentImage() được gọi để cập nhật currentImage
                ))}
            </div>
        </div>
    )
}

export default ProductImageComponent
