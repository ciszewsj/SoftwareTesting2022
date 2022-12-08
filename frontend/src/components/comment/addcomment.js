import {useState} from "react";

export default function AddComment({onAddComment}) {
    let [comment, setComment] = useState("")

    return <div>
        <label htmlFor="username">Write comment</label>
        <div className="align-text-top">
            <textarea maxLength={100} className="me-2" placeholder="Write your comment"
                      onChange={(e) => setComment(e.target.value)}/>
            <div>
                <button className="btn btn-outline-primary" onClick={() => onAddComment(comment)}>Add your comment
                </button>
            </div>
        </div>
    </div>
}