export default function Product({product}) {
    return <div>
        <h5>Name: {product.name}</h5>
        Price: ${product.price}<br/>
        Status: {product.status}
    </div>
}