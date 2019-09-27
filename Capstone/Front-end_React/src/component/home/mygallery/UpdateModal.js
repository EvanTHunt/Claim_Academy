import React, { Component } from 'react';
import AddPhotoForm from '../addphoto/AddPhotoForm';
import axios from 'axios'

class UpdateModal extends Component {
  imageUpdateHandler = (event) => {
     event.preventDefault();
     axios.post("http://localhost:4200/capstone/updateImage", this.props.image)
     .then(response => {
      console.log(this.props.image);
      this.props.close();
     }).catch(error => {
       // display error message
       window.alert('error updating image');
     });
  }
  
  render() {
    return (
      <div className={this.props.image ? "modal fade show" : "modal fade"} id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div className="modal-dialog modal-lg modal-dialog-centered" role="document">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLongTitle">Update Photo</h5>
              <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true" onClick={this.props.close} >&times;</span>
              </button>
            </div>
            <div className="modal-body">
              
              <AddPhotoForm center={{ lat: this.props.image.lat, lng: this.props.image.lng}} hideFileAttach={true} 
                image={this.props.image} imageSubmitHandler={this.imageSubmitHandler} handleClickMap={this.props.handleClickMap} 
                formChangeHandler={this.props.formChangeHandler} fileChangeHandler={this.props.fileChangeHandler}/>

            </div>
            <div className="modal-footer">
              <button onClick={this.props.close} type="button" className="btn btn-secondary" data-dismiss="modal">Cancel</button>
              <button onClick={this.imageUpdateHandler} type="button" className="btn btn-primary">Save changes</button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default UpdateModal;