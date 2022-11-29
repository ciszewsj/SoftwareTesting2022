export let GetProductsRequest = (setError, setProducts) => {
    fetch('http://localhost:8070/product',
        {
            "mode": "cors",
            "method": "GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        }
    )
        .then(response => {
            if (response.status === 200) {
                response.json().then(json => {
                    console.log(json)
                    setProducts(JSON.parse(json))
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
