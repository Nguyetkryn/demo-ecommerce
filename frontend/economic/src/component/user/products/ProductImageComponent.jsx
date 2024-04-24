import React, { useState } from 'react';
import './style.css';
const ProductImageComponent = ({ productImages }) => {

    //nếu productImages tồn tại ít nhất mội phần tử, currentImage sẽ được khởi tạo với hình ảnh đầu tiên
    const [currentImage, setCurrentImage] = useState(productImages && productImages.length > 0 ? productImages[0] : null);

    if (!productImages || productImages.length === 0) {
        return <p className='notify'>This product has no images.</p>
    }

    return (
        <div>
            {
                currentImage && <img src={currentImage} alt='current' className='large-image'/>
            }
            <div className='thumbnail-container'>
                {productImages.map((image, index) => (
                    <img 
                        key={index} 
                        className='product-thumbnail' 
                        src={image.imageUrl} 
                        alt={`Image ${index + 1}`} 
                        onMouseOver={() => setCurrentImage(image.imageUrl)}/>//khi di chuột qua mỗi hình ảnh nhỏ (onMouseOver), setCurrentImage() được gọi để cập nhật currentImage
                ))}
            </div>
        </div>
    )
}

export default ProductImageComponent
