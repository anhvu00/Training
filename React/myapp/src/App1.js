import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import App from "./App";

class App1 extends Component {
    render() {
        return (
            <div>
                <meta charSet="utf-8" />
                <meta httpEquiv="X-UA-Compatible" content="IE=edge" />
                <title>unapp Template</title>
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <meta name="description" content />
                <meta name="keywords" content />
                <meta name="author" content />
                {/* Facebook and Twitter integration */}
                <meta property="og:title" content />
                <meta property="og:image" content />
                <meta property="og:url" content />
                <meta property="og:site_name" content />
                <meta property="og:description" content />
                <meta name="twitter:title" content />
                <meta name="twitter:image" content />
                <meta name="twitter:url" content />
                <meta name="twitter:card" content />
                <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600" rel="stylesheet" />
                <link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400" rel="stylesheet" />
                {/* Animate.css */}
                <link rel="stylesheet" href="css/animate.css" />
                {/* Icomoon Icon Fonts*/}
                <link rel="stylesheet" href="css/icomoon.css" />
                {/* Bootstrap  */}
                <link rel="stylesheet" href="css/bootstrap.css" />
                {/* Magnific Popup */}
                <link rel="stylesheet" href="css/magnific-popup.css" />
                {/* Owl Carousel */}
                <link rel="stylesheet" href="css/owl.carousel.min.css" />
                <link rel="stylesheet" href="css/owl.theme.default.min.css" />
                {/* Theme style  */}
                <link rel="stylesheet" href="css/style.css" />
                {/* Modernizr JS */}
                {/* FOR IE9 below */}
                {/*[if lt IE 9]>

	<![endif]*/}
                <div className="colorlib-loader" />
                <div id="page">
                    <nav className="colorlib-nav" role="navigation">
                        <div className="top-menu">
                            <div className="container">
                                <div className="row">
                                    <div className="col-md-2">
                                        <div id="colorlib-logo"><a href="index.html">Unapp</a></div>
                                    </div>
                                    <div className="col-md-10 text-right menu-1">
                                        <ul>
                                            <li className="active"><a href="index.html">Home</a></li>
                                            <li className="has-dropdown">
                                                <a href="work.html">Works</a>
                                                <ul className="dropdown">
                                                    <li><a href="work-grid.html">Works grid with text</a></li>
                                                    <li><a href="work-grid-without-text.html">Works grid w/o text</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="services.html">Services</a></li>
                                            <li><a href="blog.html">Blog</a></li>
                                            <li><a href="about.html">About</a></li>
                                            <li><a href="shop.html">Shop</a></li>
                                            <li><a href="contact.html">Contact</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                    <section id="home" className="video-hero" style={{height: '700px', backgroundImage: 'url(images/cover_img_1.jpg)', backgroundSize: 'cover', backgroundPosition: 'center center', backgroundAttachment: 'fixed'}} data-section="home">
                        <div className="overlay" />
                        <a className="player" data-property="{videoURL:'https://www.youtube.com/watch?v=vqqt5p0q-eU',containment:'#home', showControls:false, autoPlay:true, loop:true, mute:true, startAt:0, opacity:1, quality:'default'}" />
                        <div className="display-t text-center">
                            <div className="display-tc">
                                <div className="container">
                                    <div className="col-md-12 col-md-offset-0">
                                        <div className="animate-box">
                                            <h2>Take on your biggest projects and goals</h2>
                                            <p>with Unapp's high quality features</p>
                                            <p><a href="gallery.html" className="btn btn-primary btn-lg btn-custom">Get premium</a></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <div className="colorlib-featured">
                        <div className="row animate-box">
                            <div className="featured-wrap">
                                <div className="owl-carousel">
                                    <div className="item">
                                        <div className="col-md-8 col-md-offset-2">
                                            <div className="featured-entry">
                                                <img className="img-responsive" src="images/dashboard_full_1.jpg" alt />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="item">
                                        <div className="col-md-8 col-md-offset-2">
                                            <div className="featured-entry">
                                                <img className="img-responsive" src="images/dashboard_full_2.jpg" alt />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="item">
                                        <div className="col-md-8 col-md-offset-2">
                                            <div className="featured-entry">
                                                <img className="img-responsive" src="images/dashboard_full_3.jpg" alt />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="colorlib-services colorlib-bg-white">
                        <div className="container">
                            <div className="row">
                                <div className="col-md-4 text-center animate-box">
                                    <div className="services">
                    <span className="icon">
                      <i className="icon-browser" />
                    </span>
                                        <div className="desc">
                                            <h3>Create your own template</h3>
                                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-4 text-center animate-box">
                                    <div className="services">
                    <span className="icon">
                      <i className="icon-download" />
                    </span>
                                        <div className="desc">
                                            <h3>Automatic Backup Data</h3>
                                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-4 text-center animate-box">
                                    <div className="services">
                    <span className="icon">
                      <i className="icon-layers" />
                    </span>
                                        <div className="desc">
                                            <h3>Page Builder</h3>
                                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="colorlib-intro">
                        <div className="container">
                            <div className="row">
                                <div className="col-md-8 col-md-offset-2 text-center colorlib-heading animate-box">
                                    <h2>Collaborate with your design team in a new way</h2>
                                    <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.</p>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12 animate-box">
                                    <span className="play"><a href="https://vimeo.com/channels/staffpicks/93951774" className="pulse popup-vimeo"><i className="icon-play3" /></a></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="colorlib-work-featured colorlib-bg-white">
                        <div className="container">
                            <div className="row mobile-wrap">
                                <div className="col-md-5 animate-box">
                                    <div className="mobile-img" style={{backgroundImage: 'url(images/mobile-2.jpg)'}} />
                                </div>
                                <div className="col-md-7 animate-box">
                                    <div className="desc">
                                        <h2>Real template creation</h2>
                                        <div className="features">
                                            <span className="icon"><i className="icon-lightbulb" /></span>
                                            <div className="f-desc">
                                                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.</p>
                                            </div>
                                        </div>
                                        <div className="features">
                                            <span className="icon"><i className="icon-circle-compass" /></span>
                                            <div className="f-desc">
                                                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name</p>
                                            </div>
                                        </div>
                                        <div className="features">
                                            <span className="icon"><i className="icon-beaker" /></span>
                                            <div className="f-desc">
                                                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.</p>
                                            </div>
                                        </div>
                                        <p><a href="#" className="btn btn-primary btn-outline with-arrow">Start collaborating <i className="icon-arrow-right3" /></a></p>
                                    </div>
                                </div>
                            </div>
                            <div className="row mobile-wrap">
                                <div className="col-md-5 col-md-push-7 animate-box">
                                    <div className="mobile-img" style={{backgroundImage: 'url(images/mobile-1.jpg)'}} />
                                </div>
                                <div className="col-md-7 col-md-pull-5 animate-box">
                                    <div className="desc">
                                        <h2>Finish template creation</h2>
                                        <div className="features">
                                            <span className="icon"><i className="icon-lightbulb" /></span>
                                            <div className="f-desc">
                                                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.</p>
                                            </div>
                                        </div>
                                        <div className="features">
                                            <span className="icon"><i className="icon-circle-compass" /></span>
                                            <div className="f-desc">
                                                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name</p>
                                            </div>
                                        </div>
                                        <div className="features">
                                            <span className="icon"><i className="icon-beaker" /></span>
                                            <div className="f-desc">
                                                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.</p>
                                            </div>
                                        </div>
                                        <p><a href="#" className="btn btn-primary btn-outline with-arrow">Start collaborating <i className="icon-arrow-right3" /></a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="colorlib-counter" className="colorlib-counters" style={{backgroundImage: 'url(images/cover_img_1.jpg)'}} data-stellar-background-ratio="0.5">
                        <div className="overlay" />
                        <div className="container">
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="col-md-4 col-sm-4 text-center animate-box">
                                        <div className="counter-entry">
                                            <span className="icon"><i className="flaticon-ribbon" /></span>
                                            <div className="desc">
                                                <span className="colorlib-counter js-counter" data-from={0} data-to={1500} data-speed={5000} data-refresh-interval={50} />
                                                <span className="colorlib-counter-label">Of customers are satisfied with our professional support</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-4 col-sm-4 text-center animate-box">
                                        <div className="counter-entry">
                                            <span className="icon"><i className="flaticon-church" /></span>
                                            <div className="desc">
                                                <span className="colorlib-counter js-counter" data-from={0} data-to={500} data-speed={5000} data-refresh-interval={50} />
                                                <span className="colorlib-counter-label">Amazing preset options to be mixed and combined</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-4 col-sm-4 text-center animate-box">
                                        <div className="counter-entry">
                                            <span className="icon"><i className="flaticon-dove" /></span>
                                            <div className="desc">
                                                <span className="colorlib-counter js-counter" data-from={0} data-to={1200} data-speed={5000} data-refresh-interval={50} />
                                                <span className="colorlib-counter-label">Average response time on live chat support channel</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="colorlib-blog">
                        <div className="container">
                            <div className="row">
                                <div className="col-md-8 col-md-offset-2 text-center colorlib-heading animate-box">
                                    <h2>News from our Blog</h2>
                                    <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.</p>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-4 animate-box">
                                    <article>
                                        <h2>Building the Mention Sales Application on Unapp</h2>
                                        <p className="admin"><span>May 12, 2018</span></p>
                                        <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life</p>
                                        <p className="author-wrap"><a href="#" className="author-img" style={{backgroundImage: 'url(images/person1.jpg)'}} /> <a href="#" className="author">by Dave Miller</a></p>
                                    </article>
                                </div>
                                <div className="col-md-4 animate-box">
                                    <article>
                                        <h2>Building the Mention Sales Application on Unapp</h2>
                                        <p className="admin"><span>May 12, 2018</span></p>
                                        <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life</p>
                                        <p className="author-wrap"><a href="#" className="author-img" style={{backgroundImage: 'url(images/person2.jpg)'}} /> <a href="#" className="author">by Dave Miller</a></p>
                                    </article>
                                </div>
                                <div className="col-md-4 animate-box">
                                    <article>
                                        <h2>Building the Mention Sales Application on Unapp</h2>
                                        <p className="admin"><span>May 12, 2018</span></p>
                                        <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life</p>
                                        <p className="author-wrap"><a href="#" className="author-img" style={{backgroundImage: 'url(images/person3.jpg)'}} /> <a href="#" className="author">by Dave Miller</a></p>
                                    </article>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="colorlib-subscribe" className="colorlib-subscribe" style={{backgroundImage: 'url(images/cover_img_1.jpg)'}} data-stellar-background-ratio="0.5">
                        <div className="overlay" />
                        <div className="container">
                            <div className="row">
                                <div className="col-md-10 col-md-offset-1 text-center colorlib-heading animate-box">
                                    <h2>Already trusted by over 10,000 users</h2>
                                    <p>Subscribe to receive unapp tips from instructors right to your inbox.</p>
                                </div>
                            </div>
                            <div className="row animate-box">
                                <div className="col-md-6 col-md-offset-3">
                                    <div className="row">
                                        <div className="col-md-12">
                                            <form className="form-inline qbstp-header-subscribe">
                                                <div className="col-three-forth">
                                                    <div className="form-group">
                                                        <input type="text" className="form-control" id="email" placeholder="Enter your email" />
                                                    </div>
                                                </div>
                                                <div className="col-one-third">
                                                    <div className="form-group">
                                                        <button type="submit" className="btn btn-primary">Subscribe Now</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="colorlib-pricing">
                        <div className="container">
                            <div className="row">
                                <div className="col-md-8 col-md-offset-2 text-center colorlib-heading animate-box">
                                    <h2>Pricing</h2>
                                    <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name</p>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-3 text-center animate-box">
                                    <div className="pricing">
                                        <h2 className="pricing-heading">Starter</h2>
                                        <div className="price"><sup className="currency">$</sup>9<small>per month</small></div>
                                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                        <p><a href="#" className="btn btn-primary">Select Plan</a></p>
                                    </div>
                                </div>
                                <div className="col-md-3 text-center animate-box">
                                    <div className="pricing">
                                        <h2 className="pricing-heading">Basic</h2>
                                        <div className="price"><sup className="currency">$</sup>27<small>per month</small></div>
                                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                        <p><a href="#" className="btn btn-primary">Select Plan</a></p>
                                    </div>
                                </div>
                                <div className="col-md-3 text-center animate-box">
                                    <div className="pricing">
                                        <h2 className="pricing-heading">Pro</h2>
                                        <div className="price"><sup className="currency">$</sup>74<small>per month</small></div>
                                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                        <p><a href="#" className="btn btn-primary">Select Plan</a></p>
                                    </div>
                                </div>
                                <div className="col-md-3 text-center animate-box">
                                    <div className="pricing">
                                        <h2 className="pricing-heading">Unlimited</h2>
                                        <div className="price"><sup className="currency">$</sup>140<small>per month</small></div>
                                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                        <p><a href="#" className="btn btn-primary">Select Plan</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <footer id="colorlib-footer">
                        <div className="container">
                            <div className="row row-pb-md">
                                <div className="col-md-3 colorlib-widget">
                                    <h4>About unapp</h4>
                                    <p>Far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics</p>
                                    <p>
                                    </p><ul className="colorlib-social-icons">
                                    <li><a href="#"><i className="icon-twitter" /></a></li>
                                    <li><a href="#"><i className="icon-facebook" /></a></li>
                                    <li><a href="#"><i className="icon-linkedin" /></a></li>
                                    <li><a href="#"><i className="icon-dribbble" /></a></li>
                                </ul>
                                    <p />
                                </div>
                                <div className="col-md-3 colorlib-widget">
                                    <h4>Information</h4>
                                    <p>
                                    </p><ul className="colorlib-footer-links">
                                    <li><a href="#"><i className="icon-check" /> Home</a></li>
                                    <li><a href="#"><i className="icon-check" /> Gallery</a></li>
                                    <li><a href="#"><i className="icon-check" /> About</a></li>
                                    <li><a href="#"><i className="icon-check" /> Blog</a></li>
                                    <li><a href="#"><i className="icon-check" /> Contact</a></li>
                                    <li><a href="#"><i className="icon-check" /> Privacy</a></li>
                                </ul>
                                    <p />
                                </div>
                                <div className="col-md-3 colorlib-widget">
                                    <h4>Recent Blog</h4>
                                    <div className="f-blog">
                                        <a href="blog.html" className="blog-img" style={{backgroundImage: 'url(images/blog-1.jpg)'}}>
                                        </a>
                                        <div className="desc">
                                            <h2><a href="blog.html">Photoshoot Technique</a></h2>
                                            <p className="admin"><span>30 March 2018</span></p>
                                        </div>
                                    </div>
                                    <div className="f-blog">
                                        <a href="blog.html" className="blog-img" style={{backgroundImage: 'url(images/blog-2.jpg)'}}>
                                        </a>
                                        <div className="desc">
                                            <h2><a href="blog.html">Camera Lens Shoot</a></h2>
                                            <p className="admin"><span>30 March 2018</span></p>
                                        </div>
                                    </div>
                                    <div className="f-blog">
                                        <a href="blog.html" className="blog-img" style={{backgroundImage: 'url(images/blog-3.jpg)'}}>
                                        </a>
                                        <div className="desc">
                                            <h2><a href="blog.html">Imahe the biggest photography studio</a></h2>
                                            <p className="admin"><span>30 March 2018</span></p>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-3 colorlib-widget">
                                    <h4>Contact Info</h4>
                                    <ul className="colorlib-footer-links">
                                        <li>291 South 21th Street, <br /> Suite 721 New York NY 10016</li>
                                        <li><a href="tel://1234567920"><i className="icon-phone" /> + 1235 2355 98</a></li>
                                        <li><a href="mailto:info@yoursite.com"><i className="icon-envelope" /> info@yoursite.com</a></li>
                                        <li><a href="#"><i className="icon-location4" /> yourwebsite.com</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div className="copy">
                            <div className="container">
                                <div className="row">
                                    <div className="col-md-12 text-center">
                                        <p>
                                            {/* Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. */}
                                            Copyright © All rights reserved | This template is made with <i className="icon-heart" aria-hidden="true" /> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                                            {/* Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. */}<br />
                                            Demo Images: <a href="http://unsplash.co/" target="_blank">Unsplash</a>, <a href="http://pexels.com/" target="_blank">Pexels</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </footer>
                </div>
                <div className="gototop js-top">
                    <a href="#" className="js-gotop"><i className="icon-arrow-up2" /></a>
                </div>
                {/* jQuery */}
                {/* jQuery Easing */}
                {/* Bootstrap */}
                {/* Waypoints */}
                {/* Stellar Parallax */}
                {/* YTPlayer */}
                {/* Owl carousel */}
                {/* Magnific Popup */}
                {/* Counters */}
                {/* Main */}
            </div>
        );
    }
}

export default App1;