import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import HeaderComponent from './component/admin/theme/HeaderComponent'
import FooterComponent from './component/admin/theme/FooterComponent'
import LoginComponent from './component/login/LoginComponent'
import HomePageComponent from './component/admin/HomePageComponent'
import AccountComponent from './component/user/account/AccountComponent'
import HomePage from './component/user/homePage/HomePage'
import ProductComponent from './component/user/products/ProductComponent'
import ProductDetailComponent from './component/user/products/ProductDetailComponent'

function App() {

  return (
    <>
        <BrowserRouter>
          <HeaderComponent />
          <Routes>
            {/* Admin, User */}
            <Route path='/' element={<HomePage />} />
            <Route path='/products' element={<ProductComponent />}/>
            <Route path='/product/:id' element={<ProductDetailComponent />}/>
            {/* Login, Logout, Register */}
            <Route path='/login' element={<LoginComponent />} />
            {/* Admin */}
            <Route path='/admin/home' element={<HomePageComponent />} />
            <Route path='/account' element={<AccountComponent />} />
          </Routes>
          <FooterComponent />
        </BrowserRouter>
    </>
  )
}

export default App
