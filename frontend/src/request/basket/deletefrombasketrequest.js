import {getSession} from "../../controllers/sessioncontroller";

export let DeleteFromBasketRequest = (id, numberOfItems, setError, setProducts) => {
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
                response.json().then(json => {
                    console.log(json)
                    setProducts(products => products.filter(function(value) {
                        return value !== id
                    }))
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
