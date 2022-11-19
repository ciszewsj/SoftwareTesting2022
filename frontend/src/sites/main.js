export default function MainSite() {
    function Site() {
        return (
            <div className="container p-3 m-auto">

                <form className="form-inline" action="/action_page.php">
                    <div className="form-group">
                        <label htmlFor="parcel_id">Parcel number:</label>
                        <input type="text" className="form-control" id="parcel_id"/>
                    </div>
                    <button type="submit" className="btn btn-light btn-lg p-3 m-sm-1">Search</button>
                </form>

            </div>
        )
    }

    return Site();
}
