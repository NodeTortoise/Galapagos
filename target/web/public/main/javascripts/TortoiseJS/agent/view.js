(function() {
  var Drawer, FOLLOW, OBSERVE, PatchDrawer, RIDE, SpotlightDrawer, TurtleDrawer, View, WATCH,
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; },
    __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  window.AgentStreamController = (function() {
    function AgentStreamController(container, fontSize) {
      this.container = container;
      this.mouseYcor = __bind(this.mouseYcor, this);
      this.mouseXcor = __bind(this.mouseXcor, this);
      this.view = new View(fontSize);
      this.turtleDrawer = new TurtleDrawer(this.view);
      this.patchDrawer = new PatchDrawer(this.view);
      this.spotlightDrawer = new SpotlightDrawer(this.view);
      this.container.appendChild(this.view.visibleCanvas);
      this.mouseDown = false;
      this.mouseInside = false;
      this.mouseX = 0;
      this.mouseY = 0;
      this.initMouseTracking();
      this.model = new AgentModel();
      this.model.world.turtleshapelist = defaultShapes;
      this.repaint();
    }

    AgentStreamController.prototype.mouseXcor = function() {
      return this.view.xPixToPcor(this.mouseX);
    };

    AgentStreamController.prototype.mouseYcor = function() {
      return this.view.yPixToPcor(this.mouseY);
    };

    AgentStreamController.prototype.initMouseTracking = function() {
      this.view.visibleCanvas.addEventListener('mousedown', (function(_this) {
        return function(e) {
          return _this.mouseDown = true;
        };
      })(this));
      document.addEventListener('mouseup', (function(_this) {
        return function(e) {
          return _this.mouseDown = false;
        };
      })(this));
      this.view.visibleCanvas.addEventListener('mouseenter', (function(_this) {
        return function(e) {
          return _this.mouseInside = true;
        };
      })(this));
      this.view.visibleCanvas.addEventListener('mouseleave', (function(_this) {
        return function(e) {
          return _this.mouseInside = false;
        };
      })(this));
      return this.view.visibleCanvas.addEventListener('mousemove', (function(_this) {
        return function(e) {
          var rect;
          rect = _this.view.visibleCanvas.getBoundingClientRect();
          _this.mouseX = e.clientX - rect.left;
          return _this.mouseY = e.clientY - rect.top;
        };
      })(this));
    };

    AgentStreamController.prototype.repaint = function() {
      this.view.transformToWorld(this.model.world);
      this.patchDrawer.repaint(this.model);
      this.turtleDrawer.repaint(this.model);
      this.spotlightDrawer.repaint(this.model);
      return this.view.repaint(this.model);
    };

    AgentStreamController.prototype.applyUpdate = function(modelUpdate) {
      return this.model.update(modelUpdate);
    };

    AgentStreamController.prototype.update = function(modelUpdate) {
      var u, updates, _i, _len;
      updates = Array.isArray(modelUpdate) ? modelUpdate : [modelUpdate];
      for (_i = 0, _len = updates.length; _i < _len; _i++) {
        u = updates[_i];
        this.applyUpdate(u);
      }
      return this.repaint();
    };

    return AgentStreamController;

  })();

  OBSERVE = 0;

  RIDE = 1;

  FOLLOW = 2;

  WATCH = 3;

  View = (function() {
    function View(fontSize) {
      this.fontSize = fontSize;
      this.canvas = document.createElement('canvas');
      this.ctx = this.canvas.getContext('2d');
      this.visibleCanvas = document.createElement('canvas');
      this.visibleCanvas["class"] = 'netlogo-canvas';
      this.visibleCanvas.width = 500;
      this.visibleCanvas.height = 500;
      this.visibleCanvas.style.width = "100%";
      this.visibleCtx = this.visibleCanvas.getContext('2d');
    }

    View.prototype.transformToWorld = function(world) {
      return this.transformCanvasToWorld(world, this.canvas, this.ctx);
    };

    View.prototype.transformCanvasToWorld = function(world, canvas, ctx) {
      this.quality = window.devicePixelRatio != null ? window.devicePixelRatio : 1;
      this.maxpxcor = world.maxpxcor != null ? world.maxpxcor : 25;
      this.minpxcor = world.minpxcor != null ? world.minpxcor : -25;
      this.maxpycor = world.maxpycor != null ? world.maxpycor : 25;
      this.minpycor = world.minpycor != null ? world.minpycor : -25;
      this.patchsize = world.patchsize != null ? world.patchsize : 9;
      this.wrapX = world.wrappingallowedinx;
      this.wrapY = world.wrappingallowediny;
      this.onePixel = 1 / this.patchsize;
      this.worldWidth = this.maxpxcor - this.minpxcor + 1;
      this.worldHeight = this.maxpycor - this.minpycor + 1;
      this.worldCenterX = (this.maxpxcor + this.minpxcor) / 2;
      this.worldCenterY = (this.maxpycor + this.minpycor) / 2;
      this.centerX = this.worldWidth / 2;
      this.centerY = this.worldHeight / 2;
      canvas.width = this.worldWidth * this.patchsize * this.quality;
      canvas.height = this.worldHeight * this.patchsize * this.quality;
      ctx.font = this.fontSize + 'px "Lucida Grande", sans-serif';
      ctx.imageSmoothingEnabled = false;
      ctx.webkitImageSmoothingEnabled = false;
      ctx.mozImageSmoothingEnabled = false;
      ctx.oImageSmoothingEnabled = false;
      return ctx.msImageSmoothingEnabled = false;
    };

    View.prototype.usePatchCoordinates = function(drawFn) {
      var h, w;
      this.ctx.save();
      w = this.canvas.width;
      h = this.canvas.height;
      this.ctx.setTransform(w / this.worldWidth, 0, 0, -h / this.worldHeight, -(this.minpxcor - .5) * w / this.worldWidth, (this.maxpycor + .5) * h / this.worldHeight);
      drawFn();
      return this.ctx.restore();
    };

    View.prototype.offsetX = function() {
      return this.worldCenterX - this.centerX;
    };

    View.prototype.offsetY = function() {
      return this.worldCenterY - this.centerY;
    };

    View.prototype.xPixToPcor = function(x) {
      return (this.worldWidth * x / this.visibleCanvas.clientWidth + this.worldWidth - this.offsetX()) % this.worldWidth + this.minpxcor - .5;
    };

    View.prototype.yPixToPcor = function(y) {
      return (-this.worldHeight * y / this.visibleCanvas.clientHeight + 2 * this.worldHeight - this.offsetY()) % this.worldHeight + this.minpycor - .5;
    };

    View.prototype.xPcorToCanvas = function(x) {
      return (x - this.minpxcor + .5) / this.worldWidth * this.visibleCanvas.width;
    };

    View.prototype.yPcorToCanvas = function(y) {
      return (this.maxpycor + .5 - y) / this.worldHeight * this.visibleCanvas.height;
    };

    View.prototype.drawLabel = function(xcor, ycor, label, color) {
      label = label != null ? label.toString() : '';
      if (label.length > 0) {
        return this.drawWrapped(xcor, ycor, label.length * this.fontSize / this.onePixel, (function(_this) {
          return function(x, y) {
            _this.ctx.save();
            _this.ctx.translate(x, y);
            _this.ctx.scale(_this.onePixel, -_this.onePixel);
            _this.ctx.textAlign = 'end';
            _this.ctx.fillStyle = netlogoColorToCSS(color);
            _this.ctx.fillText(label, 0, 0);
            return _this.ctx.restore();
          };
        })(this));
      }
    };

    View.prototype.drawWrapped = function(xcor, ycor, size, drawFn) {
      var x, xs, y, ys, _i, _len, _results;
      xs = this.wrapX ? [xcor - this.worldWidth, xcor, xcor + this.worldWidth] : [xcor];
      ys = this.wrapY ? [ycor - this.worldHeight, ycor, ycor + this.worldHeight] : [ycor];
      _results = [];
      for (_i = 0, _len = xs.length; _i < _len; _i++) {
        x = xs[_i];
        if ((x + size / 2) > this.minpxcor - 0.5 && (x - size / 2) < this.maxpxcor + 0.5) {
          _results.push((function() {
            var _j, _len1, _results1;
            _results1 = [];
            for (_j = 0, _len1 = ys.length; _j < _len1; _j++) {
              y = ys[_j];
              if ((y + size / 2) > this.minpycor - 0.5 && (y - size / 2) < this.maxpycor + 0.5) {
                _results1.push(drawFn(x, y));
              } else {
                _results1.push(void 0);
              }
            }
            return _results1;
          }).call(this));
        } else {
          _results.push(void 0);
        }
      }
      return _results;
    };

    View.prototype.turtleType = 1;

    View.prototype.patchType = 2;

    View.prototype.linkType = 3;

    View.prototype.watch = function(model) {
      var id, links, observer, patches, turtles, type, _ref;
      observer = model.observer, turtles = model.turtles, links = model.links, patches = model.patches;
      if (model.observer.perspective !== OBSERVE && observer.targetagent && observer.targetagent[1] >= 0) {
        _ref = observer.targetagent, type = _ref[0], id = _ref[1];
        switch (type) {
          case this.turtleType:
            return model.turtles[id];
          case this.patchType:
            return model.patches[id];
          case this.linkType:
            return model.links[id];
        }
      } else {
        return null;
      }
    };

    View.prototype.follow = function(model) {
      var persp;
      persp = model.observer.perspective;
      if (persp === FOLLOW || persp === RIDE) {
        return this.watch(model);
      } else {
        return null;
      }
    };

    View.prototype.repaint = function(model) {
      var dx, dy, height, target, width, x, xs, y, ys, _i, _len, _results;
      target = this.follow(model);
      this.visibleCanvas.width = this.canvas.width;
      this.visibleCanvas.height = this.canvas.height;
      if (target != null) {
        width = this.visibleCanvas.width;
        height = this.visibleCanvas.height;
        this.centerX = target.xcor;
        this.centerY = target.ycor;
        x = -this.xPcorToCanvas(this.centerX) + width / 2;
        y = -this.yPcorToCanvas(this.centerY) + height / 2;
        xs = this.wrapX ? [x - width, x, x + width] : [x];
        ys = this.wrapY ? [y - height, y, y + height] : [y];
        _results = [];
        for (_i = 0, _len = xs.length; _i < _len; _i++) {
          dx = xs[_i];
          _results.push((function() {
            var _j, _len1, _results1;
            _results1 = [];
            for (_j = 0, _len1 = ys.length; _j < _len1; _j++) {
              dy = ys[_j];
              _results1.push(this.visibleCtx.drawImage(this.canvas, dx, dy));
            }
            return _results1;
          }).call(this));
        }
        return _results;
      } else {
        this.centerX = this.worldCenterX;
        this.centerY = this.worldCenterY;
        return this.visibleCtx.drawImage(this.canvas, 0, 0);
      }
    };

    return View;

  })();

  Drawer = (function() {
    function Drawer(view) {
      this.view = view;
    }

    return Drawer;

  })();

  SpotlightDrawer = (function(_super) {
    __extends(SpotlightDrawer, _super);

    function SpotlightDrawer(view) {
      this.view = view;
    }

    SpotlightDrawer.prototype.dimmed = "rgba(0, 0, 50, " + (100 / 255) + ")";

    SpotlightDrawer.prototype.spotlightInnerBorder = "rgba(200, 255, 255, " + (100 / 255) + ")";

    SpotlightDrawer.prototype.spotlightOuterBorder = "rgba(200, 255, 255, " + (50 / 255) + ")";

    SpotlightDrawer.prototype.clear = 'white';

    SpotlightDrawer.prototype.outer = function() {
      return 10 / this.view.patchsize;
    };

    SpotlightDrawer.prototype.middle = function() {
      return 8 / this.view.patchsize;
    };

    SpotlightDrawer.prototype.inner = function() {
      return 4 / this.view.patchsize;
    };

    SpotlightDrawer.prototype.drawCircle = function(x, y, innerDiam, outerDiam, color) {
      var ctx;
      ctx = this.view.ctx;
      ctx.fillStyle = color;
      ctx.beginPath();
      ctx.arc(x, y, outerDiam / 2, 0, 2 * Math.PI);
      ctx.arc(x, y, innerDiam / 2, 0, 2 * Math.PI, true);
      return ctx.fill();
    };

    SpotlightDrawer.prototype.drawSpotlight = function(xcor, ycor, size, dimOther) {
      var ctx;
      ctx = this.view.ctx;
      ctx.lineWidth = this.view.onePixel;
      ctx.beginPath();
      if (dimOther) {
        this.view.drawWrapped(xcor, ycor, size + this.outer(), (function(_this) {
          return function(x, y) {
            ctx.moveTo(x, y);
            return ctx.arc(x, y, (size + _this.outer()) / 2, 0, 2 * Math.PI, true);
          };
        })(this));
        ctx.rect(this.view.minpxcor - 0.5, this.view.minpycor - 0.5, this.view.worldWidth, this.view.worldHeight);
        ctx.fillStyle = this.dimmed;
        ctx.fill();
      }
      return this.view.drawWrapped(xcor, ycor, size + this.outer(), (function(_this) {
        return function(x, y) {
          _this.drawCircle(x, y, size, size + _this.outer(), _this.dimmed);
          _this.drawCircle(x, y, size, size + _this.middle(), _this.spotlightOuterBorder);
          return _this.drawCircle(x, y, size, size + _this.inner(), _this.spotlightInnerBorder);
        };
      })(this));
    };

    SpotlightDrawer.prototype.adjustSize = function(size) {
      return Math.max(size, this.view.worldWidth / 16, this.view.worldHeight / 16);
    };

    SpotlightDrawer.prototype.dimensions = function(agent) {
      if (agent.xcor != null) {
        return [agent.xcor, agent.ycor, 2 * agent.size];
      } else if (agent.pxcor != null) {
        return [agent.pxcor, agent.pycor, 2];
      } else {
        return [agent.midpointx, agent.midpointy, agent.size];
      }
    };

    SpotlightDrawer.prototype.repaint = function(model) {
      return this.view.usePatchCoordinates((function(_this) {
        return function() {
          var size, watched, xcor, ycor, _ref;
          watched = _this.view.watch(model);
          if (watched != null) {
            _ref = _this.dimensions(watched), xcor = _ref[0], ycor = _ref[1], size = _ref[2];
            return _this.drawSpotlight(xcor, ycor, _this.adjustSize(size), model.observer.perspective === WATCH);
          }
        };
      })(this));
    };

    return SpotlightDrawer;

  })(Drawer);

  TurtleDrawer = (function(_super) {
    __extends(TurtleDrawer, _super);

    function TurtleDrawer(view) {
      this.view = view;
      this.turtleShapeDrawer = new CachingShapeDrawer({});
      this.linkDrawer = new LinkDrawer(this.view, {});
    }

    TurtleDrawer.prototype.drawTurtle = function(turtle) {
      var size, xcor, ycor;
      if (!turtle['hidden?']) {
        xcor = turtle.xcor;
        ycor = turtle.ycor;
        size = turtle.size;
        this.view.drawWrapped(xcor, ycor, size, ((function(_this) {
          return function(x, y) {
            return _this.drawTurtleAt(turtle, x, y);
          };
        })(this)));
        return this.view.drawLabel(xcor + turtle.size / 2, ycor - turtle.size / 2, turtle.label, turtle['label-color']);
      }
    };

    TurtleDrawer.prototype.drawTurtleAt = function(turtle, xcor, ycor) {
      var angle, ctx, heading, scale, shape, shapeName;
      ctx = this.view.ctx;
      heading = turtle.heading;
      scale = turtle.size;
      angle = (180 - heading) / 360 * 2 * Math.PI;
      shapeName = turtle.shape;
      shape = this.turtleShapeDrawer.shapes[shapeName] || defaultShape;
      ctx.save();
      ctx.translate(xcor, ycor);
      if (shape.rotate) {
        ctx.rotate(angle);
      } else {
        ctx.rotate(Math.PI);
      }
      ctx.scale(scale, scale);
      this.turtleShapeDrawer.drawShape(ctx, turtle.color, shapeName);
      return ctx.restore();
    };

    TurtleDrawer.prototype.drawLink = function(link, turtles, wrapX, wrapY) {
      return this.linkDrawer.draw(link, turtles, wrapX, wrapY);
    };

    TurtleDrawer.prototype.repaint = function(model) {
      var links, turtles, world;
      world = model.world;
      turtles = model.turtles;
      links = model.links;
      if (world.turtleshapelist !== this.turtleShapeDrawer.shapes && (world.turtleshapelist != null)) {
        this.turtleShapeDrawer = new CachingShapeDrawer(world.turtleshapelist);
      }
      if (world.linkshapelist !== this.linkDrawer.shapes && (world.linkshapelist != null)) {
        this.linkDrawer = new LinkDrawer(this.view, world.linkshapelist);
      }
      return this.view.usePatchCoordinates((function(_this) {
        return function() {
          var id, link, turtle, _results;
          for (id in links) {
            link = links[id];
            _this.drawLink(link, turtles, world.wrappingallowedinx, world.wrappingallowediny);
          }
          _this.view.ctx.lineWidth = _this.onePixel;
          _results = [];
          for (id in turtles) {
            turtle = turtles[id];
            _results.push(_this.drawTurtle(turtle));
          }
          return _results;
        };
      })(this));
    };

    return TurtleDrawer;

  })(Drawer);

  PatchDrawer = (function() {
    function PatchDrawer(view) {
      this.view = view;
      this.scratchCanvas = document.createElement('canvas');
      this.scratchCtx = this.scratchCanvas.getContext('2d');
    }

    PatchDrawer.prototype.colorPatches = function(patches) {
      var b, g, height, i, imageData, j, maxX, maxY, minX, minY, numPatches, patch, r, trans, width, _i, _ref;
      width = this.view.worldWidth;
      height = this.view.worldHeight;
      minX = this.view.minpxcor;
      maxX = this.view.maxpxcor;
      minY = this.view.minpycor;
      maxY = this.view.maxpycor;
      this.scratchCanvas.width = width;
      this.scratchCanvas.height = height;
      imageData = this.scratchCtx.createImageData(width, height);
      numPatches = ((maxY - minY) * width + (maxX - minX)) * 4;
      for (i = _i = 0; 0 <= numPatches ? _i < numPatches : _i > numPatches; i = 0 <= numPatches ? ++_i : --_i) {
        patch = patches[i];
        if (patch != null) {
          j = 4 * i;
          _ref = netlogoColorToRGB(patch.pcolor), r = _ref[0], g = _ref[1], b = _ref[2];
          imageData.data[j + 0] = r;
          imageData.data[j + 1] = g;
          imageData.data[j + 2] = b;
          imageData.data[j + 3] = 255;
        }
      }
      this.scratchCtx.putImageData(imageData, 0, 0);
      trans = minY + maxY;
      return this.view.ctx.drawImage(this.scratchCanvas, 0, 0, this.view.canvas.width, this.view.canvas.height);
    };

    PatchDrawer.prototype.labelPatches = function(patches) {
      return this.view.usePatchCoordinates((function(_this) {
        return function() {
          var ignore, patch, _results;
          _results = [];
          for (ignore in patches) {
            patch = patches[ignore];
            _results.push(_this.view.drawLabel(patch.pxcor + .5, patch.pycor - .5, patch.plabel, patch['plabel-color']));
          }
          return _results;
        };
      })(this));
    };

    PatchDrawer.prototype.clearPatches = function() {
      this.view.ctx.fillStyle = "black";
      return this.view.ctx.fillRect(0, 0, this.view.canvas.width, this.view.canvas.height);
    };

    PatchDrawer.prototype.repaint = function(model) {
      var patches, world;
      world = model.world;
      patches = model.patches;
      if (world.patchesallblack) {
        this.clearPatches();
      } else {
        this.colorPatches(patches);
      }
      if (world.patcheswithlabels) {
        return this.labelPatches(patches);
      }
    };

    return PatchDrawer;

  })();

}).call(this);

//# sourceMappingURL=view.js.map
