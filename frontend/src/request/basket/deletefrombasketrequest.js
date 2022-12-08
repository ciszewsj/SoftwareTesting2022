import {getSession} from "../../controllers/sessioncontroller";

export let DeleteFromBasketRequest = (id, numberOfItems, setError, getBasketRequest ) => {
    fetch('http://localhost:8070/user/cart',
        {
            mode: "cors",
            method: "DELETE",
            headers: {
                Accept: '*/*',
                'Content-Type': "application/json",
                Authorization: `Bearer ${getSession().token}`
            },
            body: JSON.stringify({
                productId: id,
                numberOfItems: numberOfItems
            })
        }
    )
        .then(response => {
            console.log("status " + response.status)
            if (response.status === 200) {
                getBasketRequest()
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
