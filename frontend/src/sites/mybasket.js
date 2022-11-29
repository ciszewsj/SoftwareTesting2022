import {Container} from "react-bootstrap";

export default function MyBasketSite() {
    function Site() {
        return (
            <Container className="p-3 m-auto">
                <h1>Items in basket:</h1>
                <h1>TODO: show all items in basket</h1>
                <button type="submit" className="btn btn-light btn-lg p-3 m-sm-1">Pay</button>
            </Container>
        )
    }

    return Site();
}
