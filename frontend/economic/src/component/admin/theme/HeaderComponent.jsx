import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { currentUserName } from '../../../service/admin/LoginService'
import avatar from "/src/assets/56c52f47af3cbe6e87f293ee3dd5894d.jpg";
import './style.css';

const HeaderComponent = () => {

    const navigate = useNavigate();

    const username = currentUserName();

    const handleLogout = () => {
        // Xóa token từ localStorage
        localStorage.removeItem('token');

        // Chuyển hướng người dùng về trang chủ
        navigate('/');
    };

    return (
        <div>
            <header>
                <nav className='navbar navbar-expand-lg navbar-light bg-light'>
                    <div className="container-fluid">
                        <div className='navbar-container'>
                            <Link to={"/"} className='navbar-brand'>Husbando Shop</Link>
                        </div>

                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                                <li className="nav-item">
                                    <Link className="nav-link" aria-current="page" to={"/"}>Home</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to={"/products"}>Product</Link>
                                </li>
                                <li className="nav-item dropdown">
                                    <Link className="nav-link dropdown-toggle" to={"/"} id="navbarDropdown">
                                        Categories
                                    </Link>
                                    <ul className="dropdown-menu">
                                        <li><a className="dropdown-item" href="#">Action</a></li>
                                        <li><a className="dropdown-item" href="#">Another action</a></li>
                                        <li><hr className="dropdown-divider" /></li>
                                        <li><a className="dropdown-item" href="#">Something else here</a></li>
                                    </ul>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to={"/"}>Discount</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to={"/"}>About Us</Link>
                                </li>
                            </ul>
                        </div>

                        {/* <div>
                            <form className="d-flex">
                                <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                                <button className="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </div> */}

                        <div className="login-in">
                            {username !== null ? (
                                <>
                                    <div className='login-success'>
                                        <span>Welcome, {username}</span>
                                        <Link to={"/account"}>
                                            <img src={avatar} alt='avatar' />
                                        </Link>
                                        <span>giỏ hàng</span>
                                        <button onClick={handleLogout}>Logout</button>
                                    </div>
                                </>
                            ) : (
                                <Link to={"/login"}>Login</Link>
                            )}
                        </div>
                    </div>
                </nav>
            </header>
        </div>
    )
}

export default HeaderComponent
