import './Hashpost.css';

function Hashpost(props) {
    const post = props.props;
    return (
        <div className='HashPost' key={post.h_id}>
            <div className='HashPostImageContainer'>
                <img className='HashPostImage' src={'/api/images/'+post.imagelink}></img>
            </div>
            <div className='HashPostPos'>{post.geohash.pos_x.toFixed(3) + " / " + post.geohash.pos_y.toFixed(3)}</div>
            <div className='HashPostDate'>{post.geohash.date}</div>
            <div className='HashPostUser'>{post.user.name}</div>
        </div>
    );
}

export default Hashpost;
