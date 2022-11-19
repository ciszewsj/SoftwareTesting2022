import {Container} from "react-bootstrap";

export default function ParcelSite() {
    function Site() {
        return (
            <Container className="p-3 m-auto">
                <form>
                    <div className="form-group">
                        <label htmlFor="name">Email address</label>
                        <input type="text" className="form-control" id="name" aria-describedby="emailHelp"
                               placeholder="Enter name"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" className="form-control" id="password" placeholder="Password"/>
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
            </Container>
        )
    }

    return Site();
}
