import React, {useState} from "react"

import ReactDOM from 'react-dom/client';
import reportWebVitals from './help/reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AppNavigation from "./components/navbar";
import MainSite from "./sites/main";
import {Home} from "./sites/admin";
import RegisterSite from "./sites/register";
import LoginSite from "./sites/login";
import MyBasketSite from "./sites/mybasket";
import {getSession} from "./controllers/sessioncontroller";
import {LogoutSite} from "./sites/logout";

export const UserContext = React.createContext({});

function Error() {
    return (
        <h1>
            404 Not Found
        </h1>)


}

const App = () => {
    const [session, setSession] = useState(getSession())
    const value = {session, setSession}


    return (
        <BrowserRouter>
            <UserContext.Provider value={value}>
                <AppNavigation/>
                <Routes>
                    <Route path="/" element={<MainSite/>}/>
                    <Route path="/my_basket" element={<MyBasketSite/>}/>
                    <Route path="/admin" element={<Home/>}/>
                    <Route path="/register" element={<RegisterSite/>}/>
                    <Route path="/login" element={<LoginSite/>}/>
                    <Route path="/logout" element={<LogoutSite/>}/>
                    <Route path="/*" element={<Error/>}/>
                </Routes>
            </UserContext.Provider>
        </BrowserRouter>

    )
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <App/>
    </React.StrictMode>
);

reportWebVitals();
