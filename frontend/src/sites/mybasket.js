import {Container} from "react-bootstrap";
import {useState} from "react";
import Product from "../help/product";

export default function MyBasketSite() {
    function Site() {
        const [products, setProducts] = useState([
            {
                id: "1",
                name: "Test",
                price: "123.1",
                status: "Available",
                numberOfItems: "1"
            }, {
                id: "2",
                name: "Test2",
                price: "123.1",
                status: "Available",
                numberOfItems: "2"
            }
        ])

        return (
            <Container className="p-3 m-auto">
                <h1>Items in basket:</h1>
                {products.map(product => (
                    <div key={product.id} className="container square border border-2 mb-2 pb-1">
                        <Product product={product}/>
                        Amount: {product.numberOfItems}
                        <div>
                            <button type="button" className="btn btn-outline-primary" onClick={() => alert("TODO")}>
                                Remove from basket
                            </button>
                        </div>
                    </div>
                ))}
                <button type="submit" className="btn btn-primary btn-lg p-3 m-sm-1" onClick={() => alert("TODO")}>Pay</button>
            </Container>
        )
    }

    return Site();
}
