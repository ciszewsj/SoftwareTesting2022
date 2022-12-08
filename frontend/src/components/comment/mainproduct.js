import Product from "../product";
import {getSession} from "../../controllers/sessioncontroller";
import {AddCommentRequest} from "../../request/addcommentrequest";
import {AddToBasketRequest} from "../../request/basket/addtobasketrequest";
import {useState} from "react";
import AddComment from "./addcomment";

export default function MainProduct({product, setError, getProductsRequest}) {
    let [showComments, setShowComments] = useState(false)

    const addCommentRequest = (id, comment) => {
        AddCommentRequest(id, comment, setError, getProductsRequest)
    }

    const addProductsToBasket = (productId, numberOfItems) => {
        AddToBasketRequest(productId, numberOfItems, setError)
    }

    return <>
        <Product product={product}/>
        <div>
            {(product.status === "AVAILABLE" && getSession().token !== "") &&
                <button type="button" className="btn btn-primary me-2"
                        onClick={() => addProductsToBasket(product.id, 1)}>
                    Add to basket
                </button>}
            {!showComments &&
                <button type="button" className="btn btn-outline-primary" onClick={() => setShowComments(true)}>Show
                    comments</button>}
            {showComments &&
                <button type="button" className="btn btn-outline-primary" onClick={() => setShowComments(false)}>Hide
                    comments</button>}
        </div>
        {showComments && <AddComment onAddComment={(comment) => addCommentRequest(product.id, comment)}/>}
        {showComments && <div>
            <h5>Comments:</h5>
            {product.comments.length === 0 && <span>This product doesn't have any comments yet.</span>}
            {product.comments.map(comment =>
                <div className="border-bottom" key={comment.id}>
                    <span>Author: {comment.user.name}</span>
                    <div>
                        <span>{comment.comment}</span>
                    </div>
                </div>
            )}
        </div>
        }
    </>
}
