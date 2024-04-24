import React, { useEffect, useState } from 'react'
import { getProduct } from '../../../service/admin/ProductService';
import { Link, useParams } from 'react-router-dom';
import './style.css';
import { Fomater } from '../../../utils/fomater';
import ProductImageComponent from './ProductImageComponent';

const ProductDetailComponent = () => {

    /**UseState thêm trạng thái vào trong functional component
     * const [state, setState] = useState(initialState);
     * initialState: giá trị khởi tạo cho trạng thái
     * state: biến chứa giá trị hiện tại của trạng thái
     * setState: hàm giúp chúng ta cập nhật giá trị của trạng thái.
                 Khi gọi setState với 1 giá trị mới, React sẽ re-render component với giá trị mới của trạng thái
    */
    const [productName, setProductName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');
    const [size, setSize] = useState('');
    const [brand, setBrand] = useState('');
    const [amount, setAmount] = useState('');
    const [category, setCategory] = useState('');
    const [productImages, setProductIamges] = useState('');
    //const [discount, setDiscount] = useState('');

    const { id } = useParams(); //truy cập vào các tham số đường dẫn (route parameters) trong URL

    useEffect(() => {
        if (id) {
            getProduct(id).then((response) => {
                setProductName(response.data.productName);
                setDescription(response.data.description);
                setPrice(response.data.price);
                setSize(response.data.size);
                setBrand(response.data.brand);
                setAmount(response.data.amount);
                setCategory(response.data.category);
                setProductIamges(response.data.productImages);
                // setDiscount(response.data.discount);
            }).catch(error => {
                console.error('Product does not exist!', error);
            })
        }
    }, [id]);

    return (
        <div className='container'>
            <div className="content">
                <div className="path-product">
                    <Link to={'/'}>Home/</Link>
                    <Link to={'#'}>Category/</Link>
                    <p>{productName}</p>
                </div>
                <div className="product-detail">
                    <div className="top-product-content">
                        <div className="product-images">
                            <ProductImageComponent productImages={productImages} />
                        </div>
                        <div className="product-infor">
                            <h1>{productName}</h1>
                            <table>
                                <tbody>
                                    <tr>
                                        <th>Category:</th>
                                        <td>{category}</td>
                                    </tr>
                                    <tr>
                                        <th>Brand:</th>
                                        <td>{brand}</td>
                                    </tr>
                                    <tr>
                                        <th>Amount:</th>
                                        <td>{amount}</td>
                                    </tr>
                                    <tr>
                                        <th>Size</th>
                                        <td>{size}</td>
                                    </tr>
                                    <tr>
                                        <td>{Fomater(price)}</td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <button>Buy Now</button>
                                        </td>
                                        <td>
                                            <button>Add To Cart</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <div className="bottom-product-content">
                        <div className="product-description">
                            <p>{description}</p>
                        </div>
                    </div>

                </div>

                <div className="product-suggestions">
                    <p>Product suggestions</p>
                </div>
            </div>
        </div>
    )
}

export default ProductDetailComponent
