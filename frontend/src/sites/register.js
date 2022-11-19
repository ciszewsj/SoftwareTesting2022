import {useContext, useEffect} from "react";
import {UserContext} from "../index";
import {Navigate} from "react-router-dom";
import {useState} from "react";
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
                        <label htmlFor="name">Name</label>
                        <input type="text" className="form-control" id="name" aria-describedby="nameHelp"
                               placeholder="Enter name" onChange={e => setFields(() => {
                            fields.name = e.target.value;
                            return fields;
                        })}
                        />
                        {errorFields.name &&
                            <><span className="error text-danger">{errorFields.name}</span><br/></>}

                        <label htmlFor="email">Email address</label>
                        <input type="email" className="form-control" id="email" aria-describedby="emailHelp"
                               placeholder="Enter email" onChange={e => setFields(() => {
                            fields.email = e.target.value;
                            return fields;
                        })}/>
                        {errorFields.email &&
                            <><span className="error text-danger">{errorFields.email}</span><br/></>}

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

                        <label htmlFor="postCode">Post code</label>
                        <input type="text" className="form-control" id="postCode" aria-describedby="postCodeHelp"
                               placeholder="Enter post code" onChange={e => setFields(() => {
                            fields.postCode = e.target.value;
                            return fields;
                        })}
                        />
                        {errorFields.postCode &&
                            <><span className="error text-danger">{errorFields.postCode}</span><br/></>}

                        <label htmlFor="city">City</label>
                        <input type="text" className="form-control" id="city" aria-describedby="cityHelp"
                               placeholder="Enter city name" onChange={e => setFields(() => {
                            fields.city = e.target.value;
                            return fields;
                        })}/>
                        {errorFields.city &&
                            <><span className="error text-danger">{errorFields.city}</span><br/></>}

                        <label htmlFor="street">Street</label>
                        <input type="text" className="form-control" id="street" aria-describedby="streetHelp"
                               placeholder="Enter street name" onChange={e => setFields(() => {
                            fields.street = e.target.value;
                            return fields;
                        })}/>
                        {errorFields.street &&
                            <><span className="error text-danger">{errorFields.street}</span><br/></>}

                        <label htmlFor="houseNumber">House number</label>
                        <input type="text" className="form-control" id="houseNumber"
                               aria-describedby="houseHelp"
                               placeholder="Enter House number" onChange={e => setFields(() => {
                            fields.houseNumber = e.target.value;
                            return fields;
                        })}/>
                        {errorFields.houseNumber &&
                            <><span className="error text-danger">{errorFields.houseNumber}</span><br/></>}

                    </div>
                    <button type="button" className="btn btn-primary" onClick={requestRegister}>Submit</button>
                </form>
                {error.status === 201 && <Navigate to="/login"/>}
            </div>
        )
    }

    if (session.token.length === 0) {
        return Site();
    } else {
        return (<Navigate to="/"/>)
    }
}
