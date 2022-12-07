import {addSession, getSession} from "../controllers/sessioncontroller";

export let LoginRequest = (setState, setError, login, password) => {
    fetch('http://localhost:8070/auth/login',
        {
            "mode": "cors",
            "method": "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            "body": JSON.stringify(
                {
                    username: login,
                    password: password
                }
            )

        }
    )
        .then(response => {
            if (response.status === 200) {
                response.json().then(json => {
                    addSession(json.token)
                    setState(getSession())
                });
            } else {
                try {
                    response.json().then(json => {
                        setError(JSON.stringify(json));
                    })
                } catch {
                    setError(response.status);
                }
            }
        })
        .catch(e => {
            console.log(e.toString());
            setError(e.toString());
        });
}
