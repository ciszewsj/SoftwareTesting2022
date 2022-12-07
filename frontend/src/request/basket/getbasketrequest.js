import {getSession} from "../../controllers/sessioncontroller";

export let GetBasketRequest = (setError, setProducts) => {
    fetch('http://localhost:8070/user/cart',
        {
            mode: "cors",
            method: "GET",
            headers: {
                Accept: '*/*',
                Authorization: `Bearer ${getSession().token}`
            }
        }
    )
        .then(response => {
            console.log("status " + response.status)
            if (response.status === 200) {
                response.json().then(json => {
                    console.log(json)
                    setProducts(json.items)
                });
            } else {
                console.log(response)
                response.json().then(
                    json => {
                        console.log(json)
                        setError(response.status + " " + JSON.stringify(json));
                        setProducts([])
                    }
                )
            }
        })
        .catch(e => {
            console.log(e.toString());
            setError(e.toString());
            setProducts([])
        });
}
