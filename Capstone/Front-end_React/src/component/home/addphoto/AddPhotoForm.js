import React, { Component } from 'react';
import { Map, GoogleApiWrapper, Marker } from 'google-maps-react';

class AddPhotoForm extends Component {
  handleMapReady(mapProps,map) {
    map.setOptions({
      draggableCursor: "default",
      draggingCursor: "pointer"
    });
  }
  
  render() {
    return (
      <div>    
        <div className='margin-left-35per'>
          <Map
            style={{ height: '75vh', width: '60%' }}
            google={this.props.google}
            zoom={10}
            initialCenter={this.props.center}
            onClick={this.props.handleClickMap}
            onReady={this.handleMapReady}
          >
            <Marker position={{lat:this.props.image.lat, lng:this.props.image.lng}}></Marker>
          </Map>
        </div>
        <div style={{ height: '75vh', width: '30%'}}>
          <form onSubmit={this.props.imageSubmitHandler}>
            <div className="form-group">
              <label className="font-large" htmlFor="exampleFormControlInput1">&raquo;Latitude / Longitude</label>
              <input name="lat" value={this.props.image.lat} type="number" onChange={this.props.formChangeHandler} required="required" className="form-control" id="exampleFormControlInput1" placeholder="Select a location on the map"/><br></br>
              <input name="lng" value={this.props.image.lng} type="number" onChange={this.props.formChangeHandler} required="required" className="form-control" id="exampleFormControlInput1" placeholder="Select a location on the map"/>  
            </div><br></br>
            <div className="form-group">
              <label className="font-large" htmlFor="exampleFormControlInput1">&raquo;Photo title</label>
              <input name="title" value={this.props.image.title} onChange={this.props.formChangeHandler} type="text" required="required" className="form-control" id="exampleFormControlInput1" placeholder="Give your photo a unique name"/>
            </div><br></br>
            <div className="form-group">
              <label className="font-large" htmlFor="exampleFormControlTextarea1">&raquo;Description</label>
              <textarea name="description" value={this.props.image.description} onChange={this.props.formChangeHandler} required="required" className="form-control" id="exampleFormControlTextarea1" placeholder="Tell us specific details about your photo, such as the weather conditions, time of day, special location details, or camera techniques used" rows="5"></textarea>
            </div><br></br>
            <div className={this.props.hideFileAttach ? "hide" : ""}>
              <label className="font-large">&raquo;Attach Image</label>
              <input name="image" type="file" onChange={this.props.fileChangeHandler}></input>
              <br></br>
              <button type="submit" className="btn btn-secondary margin-top-20">Submit</button>
            </div><br></br>        
          </form>
        </div>
      </div>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: 'API_KEY_HERE'
})(AddPhotoForm);