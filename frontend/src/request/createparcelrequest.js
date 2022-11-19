import {getSession} from "../controllers/sessioncontroller";

export let CreateParcelRequest = (fields, setErrorFields, setError) => {
    fetch('http://localhost:8080/api/parcel',
        {
            "mode": "cors",
            "method": "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getSession().token}`
            },
            "body": JSON.stringify(
                {
                    postCode: fields.postCode,
                    city: fields.city,
                    street: fields.street,
                    houseNumber: fields.houseNumber,
                    name: fields.name,
                    email: fields.email,
                    password: fields.password
                }
            )

        }
    )
        .then(response => {
            if (response.status === 200) {
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
            console.log(getSession().token);
            console.log(e.toString());
            setError(e.toString());
        });
}