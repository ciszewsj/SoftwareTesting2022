import {useContext, useState} from "react";
import {UserContext} from "../index";
import {Navigate} from "react-router-dom";
import {RegisterRequest} from "../request/registerrequest";

export default function RegisterSite() {
    const {session} = useContext(UserContext)
    const [fields, setFields] = useState({});
    const [errorFields, setErrorFields] = useState({});
    const [error, setError] = useState({});

    const requestRegister = () => {
        setErrorFields({});
        setError({});
        if (fields.password && fields.repeatPassword !== fields.password) {
            setErrorFields(() => {
                    errorFields.repeatPassword = "Passwords in fields are different";
                    return {...errorFields};
                }
            )
        } else {
            RegisterRequest(fields, setErrorFields, setError);
        }
    }


    function Site() {
        return (
            <div className="container p-3 m-auto">
                <h1>Register:</h1>
                <form>
                    <div className="form-group">
                        <label htmlFor="name">Username</label>
                        <input type="text" className="form-control" id="name" aria-describedby="nameHelp"
                               placeholder="Enter username" onChange={e => setFields(() => {
                            fields.username = e.target.value;
                            return fields;
                        })}
                        />
                        {errorFields.username &&
                            <><span className="error text-danger">{errorFields.username}</span><br/></>}

                        <label htmlFor="password">Password</label>
                        <input type="password" className="form-control" id="password" placeholder="Password"
                               onChange={e => setFields(() => {
                                   fields.password = e.target.value;
                                   return fields;
                               })}/>
                        {errorFields.password &&
                            <><span className="error text-danger">{errorFields.password}</span><br/></>}

                        <label htmlFor="repeatPassword">Repeat password</label>
                        <input type="password" className="form-control" id="repeatPassword"
                               placeholder="Repeat password" onChange={e => setFields(() => {
                            fields.repeatPassword = e.target.value;
                            return fields;
                        })}/>
                        {errorFields.repeatPassword &&
                            <><span className="error text-danger">{errorFields.repeatPassword}</span><br/></>}
                    </div>
                    <button type="button" className="btn btn-primary" onClick={requestRegister}>Submit</button>
                </form>
                {error.status === 200 && <Navigate to="/login"/>}
            </div>
        )
    }

    if (session.token.length === 0) {
        return Site();
    } else {
        return (<Navigate to="/"/>)
    }
}
