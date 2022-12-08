import {Container} from "react-bootstrap";
import {useEffect, useState} from "react";
import Product from "../components/product";
import {GetBasketRequest} from "../request/basket/getbasketrequest";
import {DeleteFromBasketRequest} from "../request/basket/deletefrombasketrequest";
import {PayForBasketRequest} from "../request/basket/payforbasketrequest";

export default function MyBasketSite() {
    function Site() {
        const [products, setProducts] = useState([])
        const [error, setError] = useState();

        const payForBasketRequest = () => {
            PayForBasketRequest(setError, setProducts)
        }

        const removeFromBasketRequest = (id) => {
            DeleteFromBasketRequest(id, 1, setError, setProducts)
        }

        const getBasketRequest = () => {
            GetBasketRequest(setError, setProducts)
        }

        useEffect(() => {
            getBasketRequest()
        }, [])

        return (
            <Container className="p-3 m-auto">
                <h1>Items in basket:</h1>
                {error && <span className="error text-danger">{error}</span>}
                {products.length === 0 && <div><span>Looks like you have no items in your Basket</span></div>}
                {products.map(product => (
                    <div key={product.id} className="container square border border-2 mb-2 pb-1">
                        <Product product={product.item}/>
                        Amount: {product.numberOfItems}
                        <div>
                            <button type="button" className="btn btn-outline-primary"
                                    onClick={() => removeFromBasketRequest(product.id)}>
                                Remove from basket
                            </button>
                        </div>
                    </div>
                ))}
                {(!error && products.length > 0) && <button type="submit" className="btn btn-primary btn-lg p-3 m-sm-1"
                                   onClick={payForBasketRequest}>Pay
                </button>}
            </Container>
        )
    }

    return Site();
}
