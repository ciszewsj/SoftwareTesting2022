export let RegisterRequest = (fields, setErrorFields, setError) => {
    fetch('http://localhost:8070/auth/register',
        {
            "mode": "cors",
            "method": "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            "body": JSON.stringify(
                {
                    username: fields.username,
                    password: fields.password,
                }
            )

        }
    )
        .then(response => {
            console.log(response);
            let newError = () => {
                let error = {};
                error.status = response.status;
                return error;
            }
            if (response.status === 201) {
            } else if (response.status === 400) {
                response.json().then(
                    json => {
                        setErrorFields(() => {
                            let errorFields = {}
                            console.log(json)
                            for (let object in json) {
                                errorFields[json[object].field] = json[object].defaultMessage;
                            }
                            console.log(errorFields);
                            return errorFields;
                        })
                    }
                )
            } else {
                response.json().then(
                    json => {
                        console.log(json);
                        setError(response.status + " " + JSON.stringify(json));
                    }
                )
            }
            setError({
                ...newError()
            })
        })
        .catch(e => {
            console.log(e.toString());
            setError(e.toString());
        });
}
