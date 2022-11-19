import {Navigate} from "react-router-dom";
import {getSession, removeSession} from "../controllers/sessioncontroller";
import {useContext, useEffect} from "react";
import {UserContext} from "../index";

export function LogoutSite() {
    const {setSession} = useContext(UserContext)
    useEffect(() => {
        removeSession();
        setSession(getSession());
    }, [setSession]);
    return (<Navigate to="/"/>);
}