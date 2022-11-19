import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";
import {NavLink} from "react-router-dom";
import {useContext} from "react";
import {UserContext} from "../index";

export default function AppNavigation() {

    const {session} = useContext(UserContext)

    function Navigation() {
        return (<Navbar expand={"lg"} bg={"dark"} variant={"dark"}>
            <Container>
                <NavLink className={"navbar-brand"} to="/">EasyPost</NavLink>
                <Navbar.Toggle aria-controls={"navbarNavAltMarkup"}/>
                <Navbar.Collapse id="navbarNavAltMarkup">
                    <Nav className={"me-auto"}>
                        <NavLink className={"nav-item nav-link"} to="/">Main</NavLink>
                        <NavLink className={"nav-item nav-link"} to="/create">Create parcel</NavLink>
                        {(session.token.length !== 0) &&
                            <NavLink className={"nav-item nav-link"} id="nav-my-parcels" to="/my_parcels">My
                                parcels</NavLink>
                        }
                        {session.role === "Admin" &&
                            <NavLink className={"nav-item nav-link"} to="/admin">Admin</NavLink>}
                    </Nav>
                    <Nav>
                        <NavDropdown title={`${(session.token.length === 0) ? "User" : session.name}`}>
                            {(session.token.length === 0) ? <>
                                    <NavLink className="dropdown-item" id="nav-login" to="/login">Login</NavLink>
                                    <NavLink className="dropdown-item" id="nav-register" to="/register">Register</NavLink>
                                </> :
                                <>
                                    <NavLink className="dropdown-item" id="nav-logout" to="/logout">Logout</NavLink>
                                </>
                            }
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>)
    }

    return Navigation();
}
