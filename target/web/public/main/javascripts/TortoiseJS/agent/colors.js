(function() {
  var b, baseIndex, cachedNetlogoColors, color, colorTimesTen, g, i, netlogoBaseColors, netlogoColorNamesIndices, r, step, _i, _len, _ref;

  window.netlogoColorToCSS = function(netlogoColor) {
    var a, array, b, g, r, _ref;
    _ref = array = netlogoColorToRGB(netlogoColor), r = _ref[0], g = _ref[1], b = _ref[2];
    a = array.length > 3 ? array[3] : 255;
    if (a < 255) {
      return "rgba(" + r + ", " + g + ", " + b + ", " + (a / 255) + ")";
    } else {
      return "rgb(" + r + ", " + g + ", " + b + ")";
    }
  };

  window.netlogoColorToOpaqueCSS = function(netlogoColor) {
    var array, b, g, r, _ref;
    _ref = array = netlogoColorToRGB(netlogoColor), r = _ref[0], g = _ref[1], b = _ref[2];
    return "rgb(" + r + ", " + g + ", " + b + ")";
  };

  window.netlogoColorToRGB = function(netlogoColor) {
    switch (typeof netlogoColor) {
      case "number":
        return cachedNetlogoColors[Math.floor(netlogoColor * 10)];
      case "object":
        return netlogoColor.map(Math.round);
      case "string":
        return netlogoBaseColors[netlogoColorNamesIndices[netlogoColor]];
      default:
        return console.error("Unrecognized color: " + netlogoColor);
    }
  };

  netlogoColorNamesIndices = {};

  _ref = ['gray', 'red', 'orange', 'brown', 'yellow', 'green', 'lime', 'turqoise', 'cyan', 'sky', 'blue', 'violet', 'magenta', 'pink', 'black', 'white'];
  for (i = _i = 0, _len = _ref.length; _i < _len; i = ++_i) {
    color = _ref[i];
    netlogoColorNamesIndices[color] = i;
  }

  netlogoBaseColors = [[140, 140, 140], [215, 48, 39], [241, 105, 19], [156, 109, 70], [237, 237, 47], [87, 176, 58], [42, 209, 57], [27, 158, 119], [82, 196, 196], [43, 140, 190], [50, 92, 168], [123, 78, 163], [166, 25, 105], [224, 126, 149], [0, 0, 0], [255, 255, 255]];

  cachedNetlogoColors = (function() {
    var _j, _ref1, _results;
    _results = [];
    for (colorTimesTen = _j = 0; _j <= 1400; colorTimesTen = ++_j) {
      baseIndex = Math.floor(colorTimesTen / 100);
      _ref1 = netlogoBaseColors[baseIndex], r = _ref1[0], g = _ref1[1], b = _ref1[2];
      step = (colorTimesTen % 100 - 50) / 50.48 + 0.012;
      if (step < 0) {
        r += Math.floor(r * step);
        g += Math.floor(g * step);
        b += Math.floor(b * step);
      } else {
        r += Math.floor((0xFF - r) * step);
        g += Math.floor((0xFF - g) * step);
        b += Math.floor((0xFF - b) * step);
      }
      _results.push([r, g, b]);
    }
    return _results;
  })();

}).call(this);

//# sourceMappingURL=colors.js.map
