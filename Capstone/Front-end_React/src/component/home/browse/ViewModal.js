import React, { Component } from 'react';
import { EXIF } from 'exif-js'
import EXIFModal from './EXIFModal';
import OtherGalleryModal from './OtherGalleryModal';

class ViewModal extends Component {
  state = {
    metadata:undefined,
    otherUser:undefined
  };
  
  openMetadata = () => {
    const image = document.getElementById("image");
    const data = {};
    EXIF.getData(image, function(){
      const fullData = EXIF.getAllTags(this);
      data['date'] = fullData['DateTimeDigitized'];
      data['camera'] = fullData['Make'] + ' ' + fullData['Model'];
      data['lens'] = fullData['undefined'];
      data['mode'] = fullData['ExposureProgram'];
      data['focal'] = fullData['FocalLength']['numerator']/fullData['FocalLength']['denominator'] + ' mm';
      const denominator = fullData['ExposureTime']['denominator']!==1 ? '/' + fullData['ExposureTime']['denominator'] : '';
      data['shutter'] = fullData['ExposureTime']['numerator'] + denominator + ' sec';
      data['aperture'] = "f/" + fullData['FNumber']['numerator']/fullData['FNumber']['denominator'];
      data['iso'] = fullData['ISOSpeedRatings'];  
    });
    setTimeout(()=>{
      this.setState({
        metadata:data
      });
    }, 0) 
  }

  closeMetadata = () => {
    this.setState({metadata:undefined});
  }

  openOtherGallery = () => {
    this.setState({otherUser:this.props.image.email});
  }

  closeOtherGallery = () => {
    this.setState({otherUser:undefined});
  }

  render() {
    return (
      <div className={this.props.image ? "modal fade show" : "modal fade"} id="exampleModalCenter" tabIndex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div className="full-screen" role="document">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLongTitle">Photo Details</h5>
              <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true" onClick={this.props.close} >&times;</span>
              </button>
            </div>
            <div className="modal-body">
              <div className="inline">
                <img src={`data:image/jpeg;base64,${this.props.image.file}`} className="view-modal-image" id="image" alt=''/>
              </div>
              <div className="inline view-modal-text">
                <h3><span className="italic">Title: </span>"{this.props.image.title}"</h3>
                <br></br>
                <h3><span className="italic">Notes: </span></h3>
                <p>{this.props.image.description}</p>
                <br></br>
                {this.props.image.email !== JSON.parse(localStorage.getItem("loggedInStudent")).email ? 
                  <div><h3 className="italic">User: </h3><p className="link" onClick={this.openOtherGallery}>{this.props.image.email}</p></div> : ''}
                <br></br>
                <button onClick={this.openMetadata} type="button" className="btn btn-secondary" data-dismiss="modal">View Metadata</button>  
                {this.state.metadata!==undefined ? <EXIFModal metadata={this.state.metadata} close={this.closeMetadata}/> : ''}  
                {this.state.otherUser!==undefined ? <OtherGalleryModal email={this.state.otherUser} close={this.closeOtherGallery}/> : ''}      
              </div>
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

export default ViewModal;