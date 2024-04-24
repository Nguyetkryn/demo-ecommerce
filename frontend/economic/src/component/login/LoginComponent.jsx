import axios from 'axios';
import React, { useContext, useState } from 'react'
import './style.css';
import { useNavigate } from 'react-router-dom';

const LoginComponent = () => {
    
    const [ username, setUsername] = useState('');
    const [ password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await axios.post("http://localhost:8080/api/no-auth/login", { username, password });
            const token = response.data.accessToken;
            const role = response.data.role;
            localStorage.setItem('token', token);


            //Chuyển hướng người dùng dựa trên role
            if (role == 'ROLE_ADMIN') {
                navigate('/admin/home');
            } 
            else {
                navigate('/');
            }
        } catch (error) {
            console.error('Error during login', error);
        }
    };

    return (
        <div className='container-login'>
            <div className='row'>
                <div className='login-form'>
                    <h2>Welcome to Husbando Shop</h2>
                    <div className='column'>
                        <label style={{marginRight: '10px'}}>Username: </label>
                        <input type='text' value={username} onChange={(e) => setUsername(e.target.value)} placeholder='Enter username' />
                    </div>
                    <div className='column'>
                        <label style={{marginRight: '10px'}}>Password: </label>
                        <input type='password' value={password} onChange={(e) => setPassword(e.target.value)} placeholder='Enter password' />
                    </div>
                    <div className='button-login'>
                        <button onClick={handleLogin}>Login</button>
                    </div>
                    
                </div>
            </div>
        </div>
    )
}

export default LoginComponent
