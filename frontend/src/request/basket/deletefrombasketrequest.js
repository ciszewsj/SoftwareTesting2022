import {getSession} from "../../controllers/sessioncontroller";

export let DeleteFromBasketRequest = (id, setError) => {
    fetch('http://localhost:8070/user/cart',
        {
            mode: "cors",
            method: "DELETE",
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
