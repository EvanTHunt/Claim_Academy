import React, { Component } from 'react';
import {Route} from 'react-router-dom';
import Header from '../header/Header';
import SignUp from '../signup/SignUp';
import AboutUs from '../aboutus/AboutUs';
import thankYou from '../thankyou/ThankYou';
import Home from '../home/Home';
import {withRouter} from 'react-router-dom';

class Layout extends Component {
  render() {
    let routes = (
      <React.Fragment>
        <Route exact path="/" component={SignUp}/>
        <Route path="/sign-up" component={SignUp}/>
      </React.Fragment>
    );

    if(localStorage.getItem("loggedInStudent")){
      routes = (
        <React.Fragment>
          <Route exact path="/" component={Home}/>
          <Route path="/home" component={Home}/>
        </React.Fragment>
      );
    }
    
    return (
      <div>
        <Header {...this.props}/>
        {routes}
        <Route path="/about-us" component={AboutUs}/>
        <Route path="/thank-you" component={thankYou}/>
      </div>
    );
  }
}

export default withRouter(Layout);