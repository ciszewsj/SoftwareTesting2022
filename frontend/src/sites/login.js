import {useContext, useState} from "react";
import {Container} from "react-bootstrap";
import {UserContext} from "../index";
import {Navigate} from "react-router-dom";
import {LoginRequest} from "../request/loginrequest";

export default function LoginSite() {
    const [name, setName] = useState();
    const [error, setError] = useState();
    const [password, setPassword] = useState();
    const {session, setSession} = useContext(UserContext)


    function Site() {


        const requestLogin = () => {
            LoginRequest(setSession, setError, name, password);
        }

        return (
            <Container className="p-3 m-auto">
                <h1>Login:</h1>
                <form>
                    <div className={"form-group"}>
                        <label htmlFor="name">Name</label>
                        <input type="text" className={`form-control`} id="name"
                               aria-describedby="nameHelp"
                               placeholder="Name" onChange={e => setName(e.target.value)}/>

                        <label htmlFor="password">Password</label>
                        <input type="password" className="form-control" id="password" placeholder="Password"
                               onChange={e => setPassword(e.target.value)}/>
                        {error && <span className="error text-danger">{error}</span>}
                    </div>
                    <button type="button" className="btn btn-primary" onClick={requestLogin}>
                        Submit
                    </button>
                </form>
            </Container>
        )
    }

    if (session.token.length === 0) {
        return Site();
    } else {
        return (<Navigate to="/"/>)
    }
}
