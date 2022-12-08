export default function Product({product}) {
    return <div>
        <h3>Name: {product.name}</h3>
        Price: ${product.price}<br/>
        Status: {product.status}
    </div>
}