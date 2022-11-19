import {Container, Pagination, Table} from "react-bootstrap";
import {useEffect, useState} from "react";

function AppTest({parcel}) {
    return (<tr>
        <th scope="row">{parcel.id}</th>
        <td>{parcel.name}</td>
        <td>Otto</td>
        <td>@mdo</td>
        <td>
            <button className="btn btn-primary">Submit</button>
        </td>
    </tr>)
}

export const Home = () => {
    const [parcels, setParcels] = useState(null)

    useEffect(() => {
        setParcels(null);
        fetch('http://localhost:8080/order/test', {"mode": "cors"})
            .then(response => response.json())
            .then(response => {
                setParcels(response);
                console.log("stete", response);
            })
    }, [])
    return (
        <div className="home">
            <Container className="p-4 m-auto">
                <Table responsive={"md"} striped={true} border={1} variant={"light"}>
                    <thead>
                    <tr>
                        <th>parcelId</th>
                        <th>customerId</th>
                        <th>create</th>
                        <th>actual status</th>
                        <th>edit status</th>
                    </tr>
                    </thead>
                    <tbody>
                    {parcels && parcels.map(parcel => <AppTest key={parcel.id} parcel={parcel}/>)}
                    </tbody>
                </Table>
            </Container>
            )
        </div>
    );
}

// <nav aria-label="Page navigation example">
//     <Pagination className="justify-content-center">
//         <Pagination.First/>
//         <Pagination.Ellipsis disabled/>
//         <Pagination.Item active>{1}</Pagination.Item>
//         <Pagination.Item>{2}</Pagination.Item>
//         <Pagination.Item>{3}</Pagination.Item>
//         <Pagination.Ellipsis disabled/>
//         <Pagination.Last/>
//     </Pagination>
// </nav>