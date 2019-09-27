import React, { Component } from 'react';

class EXIFModal extends Component {
  render() {
    return (
      <div className={this.props.metadata ? "modal fade show" : "modal fade"} id="exampleModalCenter" tabIndex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div className="modal-dialog modal-dialog-centered" role="document">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLongTitle">Image Metadata</h5>
              <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true" onClick={this.props.close} >&times;</span>
              </button>
            </div>
            <div className="modal-body">
              <h3><img src={require("./EXIF_icons/dateTime.png")} className="" style={{width:'10%'}} alt=''/>&nbsp;&nbsp;{this.props.metadata.date}</h3>
              <br></br>
              <h3><img src={require("./EXIF_icons/camera.png")} className="" style={{width:'10%'}} alt=''/>&nbsp;&nbsp;{this.props.metadata.camera}</h3>
              <br></br>
              <h3><img src={require("./EXIF_icons/lens.png")} className="" style={{width:'10%'}} alt=''/>&nbsp;&nbsp;{this.props.metadata.lens}</h3>
              <br></br>
              <h3><img src={require("./EXIF_icons/modeDial.jpg")} className="" style={{width:'10%'}} alt=''/>&nbsp;&nbsp;{this.props.metadata.mode}</h3>
              <br></br>
              <h3><img src={require("./EXIF_icons/focal.png")} className="" style={{width:'10%'}} alt=''/>&nbsp;&nbsp;{this.props.metadata.focal}</h3>
              <br></br>
              <h3><img src={require("./EXIF_icons/aperture.png")} className="" style={{width:'10%'}} alt=''/>&nbsp;&nbsp;{this.props.metadata.aperture}</h3>
              <br></br>
              <h3><img src={require("./EXIF_icons/shutter.png")} className="" style={{width:'10%'}} alt=''/>&nbsp;&nbsp;{this.props.metadata.shutter}</h3>
              <br></br>
              <h3><img src={require("./EXIF_icons/iso.png")} className="" style={{width:'10%'}} alt=''/>&nbsp;&nbsp;{this.props.metadata.iso}</h3>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default EXIFModal;