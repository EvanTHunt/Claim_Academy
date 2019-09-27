import React, { Component } from 'react';
import { withGoogleMap, GoogleMap, Marker } from 'react-google-maps'
import axios from 'axios';
import ViewModal from './ViewModal';

class Browse extends Component {
  constructor(){
    super()
    this.state = {
      map:null,
      images:[],
      selectedImage:undefined,
      hoveredImageId:undefined
    };
  }

  componentDidMount(){
    this.props.setSelectedPage('browsephotos');
  }

  componentWillMount(){
    const bounds = {
      wBound:-93.39230224609378,
      eBound:-87.04769775390628,
      sBound:38.12725136363764,
      nBound:39.12924782721188
    }

    axios.post("http://localhost:4200/capstone/getImagesByLatLng", bounds)
    .then(response => {
      this.setState({images:response.data});
    }).catch(error => {
      // display err/or message
      window.alert('error getting image');
    });
  }

  boundsChanged = () => {    
    const wBound = this.state.map.getBounds().getSouthWest().lng();
    const eBound = this.state.map.getBounds().getNorthEast().lng();
    const sBound = this.state.map.getBounds().getSouthWest().lat();
    const nBound = this.state.map.getBounds().getNorthEast().lat();

    const bounds = {
      wBound:wBound,
      eBound:eBound,
      sBound:sBound,
      nBound:nBound
    }

    axios.post("http://localhost:4200/capstone/getImagesByLatLng", bounds)
    .then(response => {
      this.setState({images:response.data});
    }).catch(error => {
      // display error message
      window.alert('error getting image');
    });
  }

  imageHovered = (image) => {
    this.setState({
      hoveredImageId:image.id
    });
  }

  mouseOut = () => {
    this.setState({
      hoveredImageId:undefined
    });
  }

  selectImageHandler = (image) => {
    this.setState({selectedImage:image});
  }

  onModalClose = () => {
    this.setState({selectedImage:undefined});
  }

  render() {

    let markers = '';
    let data = '';
        
    if(this.state.images.length > 0){
      markers = this.state.images.map((image,index) => {
        if(image.id === this.state.hoveredImageId){
          return <Marker animation={1} position={{lat:image['lat'], lng:image['lng']}} key={index}/>
        }
        return <Marker position={{lat:image['lat'], lng:image['lng']}} key={index}/>
      })
      data = this.state.images.map((image, index) =>
          <img onClick={() => this.selectImageHandler(image)} onMouseOut={this.mouseOut} onMouseOver={() => this.imageHovered(image)} 
            src={`data:image/jpeg;base64,${image['file']}`} className="imagePadding" style={{height:'100%', cursor:'pointer'}} key={index} alt=''/>
      )
    }

    return (
      <div>
        <div className='horizontal-scroll-images'>
          {data}
        </div>
        <div>
          <GoogleMap
            ref={ (map) => {
                if(this.state.map != null)
                  return

                this.setState({map:map});
              }
            }
            defaultZoom={9}
            defaultCenter={{lat:38.63, lng: -90.22 }}
            onDragEnd={this.boundsChanged}
            onZoomChanged={this.boundsChanged}
          >
            {markers}
          </GoogleMap>
        </div>
        {this.state.selectedImage!==undefined ? <ViewModal close={this.onModalClose} image={this.state.selectedImage}/> : ''}
      </div>  
    );
  }
}

export default withGoogleMap(Browse);