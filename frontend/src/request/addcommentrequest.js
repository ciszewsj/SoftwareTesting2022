import {getSession} from "../controllers/sessioncontroller";

export let AddCommentRequest = (id, comment, setError, getProductsRequest) => {
    fetch('http://localhost:8070/user/comment',
        {
            mode: "cors",
            method: "POST",
            headers: {
                Accept: '*/*',
                'Content-Type': "application/json",
                Authorization: `Bearer ${getSession().token}`
            },
            body: JSON.stringify(
                {
                    productId: id,
                    comment: comment,
                }
            )
        }
    )
        .then(response => {
            console.log("status " + response.status)
            if (response.status === 200) {
                getProductsRequest()
                response.json().then(json => {
                    console.log(json)
                });
            } else {
                console.log(response)
                response.json().then(
                    json => {
                        console.log(json)
                        setError(response.status + " " + JSON.stringify(json));
                    }
                )
            }
        })
        .catch(e => {
            console.log(e.toString());
            setError(e.toString());
        });
}
