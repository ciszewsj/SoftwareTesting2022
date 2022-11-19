import jwt_decode from "jwt-decode";


export const addSession = (token) => {
    let name = "";
    let role = "";
    try {
        let decoded = jwt_decode(token);
        name = decoded.sub;
    } catch {
        token = "";

    }
    localStorage.setItem("Session", JSON.stringify({token: `${token}`, name: `${name}`, role: `${role}`}));
}

export const removeSession = () => {
    addSession("", "", "");
}

export const getSession = () => {
    let session = JSON.parse(localStorage.getItem("Session"));
    if (session === undefined || session === null) {
        removeSession();
        getSession();
    }
    return session;
}
