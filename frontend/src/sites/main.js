import {useEffect, useState} from "react";
import {GetProductsRequest} from "../request/productrequest";
import Product from "../help/product";

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
                            <Product product={product}/>
                            <div>
                                <button type="button" className="btn btn-primary me-2" onClick={() => alert("TODO")}>
                                    Add to basket
                                </button>
                                <button type="button" className="btn btn-outline-primary" onClick={() => alert("TODO")}>
                                    Comments
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
