import React from 'react';
import './style.css';
import avatar from "/src/assets/56c52f47af3cbe6e87f293ee3dd5894d.jpg";

const AccountComponent = () => {
    return (
        <div className='container'>
            <div className="account-information">
                <div className="top">
                    <div className="avatar-user">
                        <img src={avatar} alt='avatar'/>
                    </div>
                </div>

                <div className="bottom">
                    <div className="left">

                    </div>
                    <div className="right">
                        <table className='account'>
                            <tbody>
                                <tr>
                                    <th>Full name:</th>
                                    <td>Alex</td>
                                </tr>
                                <tr>
                                    <th>Phone number:</th>
                                    <td>1234567890</td>
                                </tr>
                                <tr>
                                    <th>Email:</th>
                                    <td>alex@gmail.com</td>
                                </tr>
                                <tr>
                                    <th>Address:</th>
                                    <td>Texas</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    )
}

export default AccountComponent
