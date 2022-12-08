import {useEffect, useState} from "react";
import {GetProductsRequest} from "../request/getproductrequest";
import MainProduct from "../components/comment/mainproduct";

export default function MainSite() {
    function Site() {
        const [products, setProducts] = useState([])
        const [error, setError] = useState();

        const getProductsRequest = () => {
            GetProductsRequest(setError, setProducts)
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
                            <MainProduct product={product} setError={setError} getProductsRequest={getProductsRequest}/>
                        </li>
                    ))}
                </ul>
            </div>
        )
    }

    return Site();
}
