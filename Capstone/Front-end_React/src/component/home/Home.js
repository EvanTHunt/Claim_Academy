import React, { Component } from 'react';
import { Link, Route } from 'react-router-dom';
import AddPhoto from './addphoto/AddPhoto';
import Browse from './browse/Browse'
import MyGallery from './mygallery/MyGallery';

class Home extends Component {
  state={
    student:'',
    selectedPage:'mygallery'
  };
  
  componentWillMount(){
    const loggedInStudent = JSON.parse(localStorage.getItem("loggedInStudent"));
    this.setState({
      student:loggedInStudent
    });
  }

  setSelectedPage = (page) => {
    this.setState({
      selectedPage:page
    });
  }

  render() {
    return (
      <div className="container-fluid home-margin">
        <div className="row">
          <nav className="col-md-2 d-none d-md-block bg-light sidebar">
            <div className="sidebar-sticky">
              <ul className="nav flex-column">
                <br></br>
                <li className="nav-item">
                    <Link className={this.state.selectedPage==='mygallery' ? "nav-link border navSelect" : "nav-link"} to="/home/mygallery">
                        <img src={require("./icons/gallery.png")} className="iconShift3up" style={{width:'15%'}} alt=''/>
                        <span className="font-150per" style={{color:'#202428'}}>&nbsp;My Gallery</span><span className="sr-only">(current)</span>
                    </Link>
                </li>
                <li className="nav-item">
                    <Link className={this.state.selectedPage==='browsephotos' ? "nav-link border navSelect" : "nav-link"} to="/home/browsephotos">
                      <img src={require("./icons/camera.png")} className="iconShift5up" style={{width:'15%'}} alt=''/>  
                      <span className="font-150per" style={{color:'#202428'}}>&nbsp;Browse Photos</span>
                      </Link>
                </li>
                <li className="nav-item">
                    <Link className={this.state.selectedPage==='addphoto' ? "nav-link border navSelect" : "nav-link"} to="/home/addphoto">
                        <img src={require("./icons/plus.png")} className="iconShift5up" style={{width:'15%'}} alt=''/>
                        <span className="font-150per" style={{color:'#202428'}}>&nbsp;Add Photo</span>
                    </Link>
                </li>
                <li className="nav-item">
                    <Link className={this.state.selectedPage==='profile' ? "nav-link border navSelect" : "nav-link"} to="/home/profile">
                      <img src={require("./icons/user.png")} className="iconShift5up" style={{width:'15%'}} alt=''/>  
                      <span className="font-150per" style={{color:'#202428'}}>&nbsp;Profile</span>
                    </Link>
                </li>
              </ul>
            </div>
          </nav>

          <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-4"><div className="chartjs-size-monitor" style={{ position: 'absolute', left: '0px', top: '0px', right: '0px', bottom: '0px', overflow: 'hidden', 'pointerEvents': 'none', visibility: 'hidden', 'zIndex': '-1' }}><div className="chartjs-size-monitor-expand" style={{ position: 'absolute', left: '0', top: '0', right: '0', bottom: '0', overflow: 'hidden', 'pointerEvents': 'none', visibility: 'hidden', 'zIndex': '-1' }}><div style={{ position: 'absolute', width: '1000000px', height: '1000000px', left: '0', top: '0' }}></div></div><div className="chartjs-size-monitor-shrink" style={{ position: 'absolute', left: '0', top: '0', right: '0', bottom: '0', overflow: 'hidden', 'pointerEvents': 'none', visibility: 'hidden', 'zIndex': '-1' }}><div style={{ position: 'absolute', width: '200%', height: '200%', left: '0', top: '0' }}></div></div></div>
            <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
              <h1 className="h2">Welcome, {this.state.student.firstName}!</h1>
            </div>
            <Route path='/home/mygallery' render={(props) => <MyGallery setSelectedPage={this.setSelectedPage}/>}/>
            <Route path='/home/browsephotos' render={(props) => <Browse setSelectedPage={this.setSelectedPage} mapElement={<div style={{height:'50vh'}}/>} containerElement={<div style={{height:'50vh'}}/>}/>}/>
            <Route path='/home/addphoto' render={(props) => <AddPhoto setSelectedPage={this.setSelectedPage}/>}/>
          </main>
          
        </div>
      </div>
    );
  }
}

export default Home;