import {useState} from "react";
import {useContext} from "react";
import {UserContext} from "../index";
import {CreateParcelRequest} from "../request/createparcelrequest";

export default function CreateParcelSite() {
    const {session} = useContext(UserContext)
    const [fields, setFields] = useState({});
    const [errorFields, setErrorFields] = useState({});
    const [error, setError] = useState({});

    const createRequest = () => {
        console.log(fields)
        CreateParcelRequest(fields, setErrorFields, setError);
    }

    function Navigation() {
        return (
            <div className="container p-3 m-auto">
                <form>
                    <div className="form-group">
                        <label htmlFor="name">Name</label>
                        <input type="text" className="form-control" id="name" aria-describedby="nameHelp"
                               placeholder="Name" onChange={e => setFields(() => {
                            fields.name = e.target.value;
                            return fields;
                        })}/>

                        <label htmlFor="email">Email address</label>
                        <input type="email" className="form-control" id="email" aria-describedby="emailHelp"
                               placeholder="Email" onChange={e => setFields(() => {
                            fields.email = e.target.value;
                            return fields;
                        })}/>
                    </div>

                    <div className="form-group">
                        <label htmlFor="postCode">Post code</label>
                        <input type="text" className="form-control" id="postCode" aria-describedby="postCodeHelp"
                               placeholder="Post code" onChange={e => setFields(() => {
                            fields.postCode = e.target.value;
                            return fields;
                        })}/>

                        <label htmlFor="city">City</label>
                        <input type="text" className="form-control" id="city" aria-describedby="emailHelp"
                               placeholder="City" onChange={e => setFields(() => {
                            fields.city = e.target.value;
                            return fields;
                        })}/>

                        <label htmlFor="street">Street</label>
                        <input type="text" className="form-control" id="street" aria-describedby="streetHelp"
                               placeholder="Street" onChange={e => setFields(() => {
                            fields.street = e.target.value;
                            return fields;
                        })}/>

                        <label htmlFor="houseNumber">House number</label>
                        <input type="text" className="form-control" id="houseNumber"
                               aria-describedby="houseNumberHelp"
                               placeholder="House number" onChange={e => setFields(() => {
                            fields.houseNumber = e.target.value;
                            return fields;
                        })}/>

                    </div>
                    <button type="button" className="btn btn-primary" onClick={createRequest}>Submit</button>
                </form>
            </div>
        )
    }

    return Navigation();
}