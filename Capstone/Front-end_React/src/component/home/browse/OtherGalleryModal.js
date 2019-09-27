import React, { Component } from 'react';
import Gallery from 'react-grid-gallery';
import axios from 'axios';

class OtherGalleryModal extends Component {
  state={
    images:[]
  };
  
  componentDidMount(){
    const student = {
      email:this.props.email,
    };

    axios.post("http://localhost:4200/capstone/getGallery", student)
    .then(response => {
      this.setState({images:response.data});
    }).catch(error => {
      // display error message
      window.alert('error getting gallery');
    });
  }  
  
  render() {
    let convertedImages = [];
    let img = null;

    if(this.state.images.length > 0){
      for(let i = 0; i < this.state.images.length; i++){
        img = new Image();
        img.src = 'data:image/jpeg;base64,' + this.state.images[i].file
        convertedImages.push({
          src:'data:image/jpeg;base64,' + this.state.images[i].file,
          thumbnail:'data:image/jpeg;base64,' + this.state.images[i].file,
          thumbnailWidth:img.width,
          thumbnailHeight:img.height,
          caption: this.state.images[i].title
        });
      }
    }
    
    return (
      <div className={this.props.email ? "modal fade show vert-scroll" : "modal fade"} id="exampleModalCenter" tabIndex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div className="modal-dialog modal-lg modal-dialog-centered" role="document">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLongTitle">User Gallery for: {this.props.email}</h5>
              <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true" onClick={this.props.close} >&times;</span>
              </button>
            </div>
            <div className="modal-body">
              <Gallery images={convertedImages} rowHeight={250} margin={4}/>
            </div>
            <div className="modal-footer">
              <button onClick={this.props.close} type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default OtherGalleryModal;