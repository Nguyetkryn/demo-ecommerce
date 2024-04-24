import axios from 'axios';

const REST_API_BASE_URL = 'http://localhost:8080/api/no-auth/login';

export const loginForm = () => axios.get(REST_API_BASE_URL);


export const currentUserName = () => {
    const token = localStorage.getItem('token');
    if(token !== null){
        // hàm decode chuỗi token gồm expiration date, initialized date, username
        const payload = JSON.parse(atob(token.split(".")[1]))
        // lấy sub là username
        const username = payload.sub;
        return username;
    }
    return null;
}
   
