import axios from 'axios';

const REST_API_BASE_URL = 'http://localhost:8080/api/no-auth/products';

export const listProduct = () => axios.get(REST_API_BASE_URL);

//Call Get Product REST API
export const getProduct = (productId) => axios.get(REST_API_BASE_URL + '/' + productId);

