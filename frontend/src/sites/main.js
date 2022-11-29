import {useEffect, useState} from "react";
import {GetProductsRequest} from "../request/productrequest";

export default function MainSite() {
    function Site() {
        const [products, setProducts] = useState([{
            id: "1",
            name: "Test",
            price: "123.1",
            status: "Available"
        }, {
            id: "2",
            name: "Test2",
            price: "123.1",
            status: "Available"
        }])
        const [error, setError] = useState();

        const createProductRequest = () => {
            GetProductsRequest(setError, setProducts)
        }

        useEffect(() => {
            createProductRequest()
        })

        return (
            <div className="container p-3 m-auto">
                <h2>Products:</h2>
                {error && <span className="error text-danger">{error}</span>}
                <ul type="none">
                    {products.map(product => (
                        <li key={product.id} className="container square border border-2 mb-2 pb-1">
                            <div>
                                <p>Name: {product.name}</p>
                            </div>
                            <div>
                                <p>Price: {product.price}</p>
                            </div>
                            <div>
                                <p>Status: {product.status}</p>
                            </div>
                            <div>
                                <button type="button" className="btn btn-primary me-2" onClick={() => alert("TODO")}>
                                    Comments
                                </button>
                                <button type="button" className="btn btn-primary" onClick={() => alert("TODO")}>
                                    Add to basket
                                </button>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        )
    }

    return Site();
}
