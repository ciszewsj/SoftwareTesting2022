import {useEffect, useState} from "react";
import {GetProductsRequest} from "../request/getproductrequest";
import Product from "../help/product";
import {AddToBasketRequest} from "../request/basket/addtobasketrequest";

export default function MainSite() {
    function Site() {
        const [products, setProducts] = useState([])
        const [error, setError] = useState();

        const getProductsRequest = () => {
            GetProductsRequest(setError, setProducts)
        }

        const addProductsToBasket = (productId, numberOfItems) => {
            AddToBasketRequest(productId, numberOfItems, setError)
        }

        useEffect(() => {
            getProductsRequest()
        }, [])

        return (
            <div className="container p-3 m-auto">
                <h2>Products:</h2>
                {error && <span className="error text-danger">{error}</span>}
                <ul type="none">
                    {products.map(product => (
                        <li key={product.id} className="container square border border-2 mb-2 pb-1">
                            <Product product={product}/>
                            <div>
                                {product.status === "AVAILABLE" &&
                                    <button type="button" className="btn btn-primary me-2"
                                            onClick={() => addProductsToBasket(product.id, 1)}>
                                        Add to basket
                                    </button>}
                                <button type="button" className="btn btn-outline-primary"
                                        onClick={() => alert("TODO")}>
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
