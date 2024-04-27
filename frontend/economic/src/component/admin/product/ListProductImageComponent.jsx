import React from 'react'

const ListProductImageComponent = ({ product }) => {

    if (!product.productImages || product.productImages.length === 0) {
        return <p className='notify'>This product has no images.</p>
    }
    
    const image = product.productImages[0]; //hiển thị ảnh đầu tiên trong danh sách productImages
    
    if (!image) {
        return <p>Sản phẩm này không có hình ảnh.</p>;
      }
    
    return (
        <div>
            <img className='thumbnail-product' src={image.imageUrl} alt={`${image.imageId}`} />
        </div>
    )
}

export default ListProductImageComponent
