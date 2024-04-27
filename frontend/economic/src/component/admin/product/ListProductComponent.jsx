import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { listProduct } from '../../../service/admin/ProductService';
import ListProductImageComponent from './ListProductImageComponent';
import { Fomater } from '../../../utils/fomater';
import './style.css'

const ListProductComponent = () => {

    const [products, setProducts] = useState([])

    const navigator = useNavigate();

    useEffect(() => {
        getAllProduct();
    }, [])

    function getAllProduct() {
        listProduct().then((resopnse) => {
            setProducts(resopnse.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function getProduct (id) {
        navigator(`/product/${id}`)
    }

    return (
        <div className='container'>
            {/* Featured Begin */}
            <div className='row'>
                <div className='featured'>
                    <hr />
                    <h2 className='title-custome'>Outstanding products</h2>
                    <div className='section-title'>
                        {products.map(product => (
                            <div key={product.productId} className='products'>
                                <div className='thumbnail-product'>
                                    <ListProductImageComponent product={product} />
                                </div>
                                <div>
                                    {/* <Link>: là một component của react-router-dom thường mong đợi một chuỗi đường dẫn
                                                hoặc một đối tượng đường dẫn, không phải 1 hàm */}
                                    {/* getProduct() trả về đường dẫn dựa trên productId 
                                    <Link to={getProduct(product.productId)} className='tittle'>{product.productName}</Link>*/}
                                    {/* getProduct() thực hiện 1 hành động khi link được click (vd, cập nhật trạng thái) => sd onClick prop */}
                                    <Link to={`/product/${product.productId}`} 
                                        onClick={() => getProduct(product.productId)} 
                                        className='tittle'>{product.productName}</Link>
                                    
                                    <br />
                                    <h5>{Fomater(product.price)}</h5>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
            {/* Featured End*/}
        </div>
    )
}

export default ListProductComponent
