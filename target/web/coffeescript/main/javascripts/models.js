(function() {
  exports.bindModelChooser = function(container, selectionChanged) {
    var createModelSelection, populateModelChoices, setModelCompilationStatus;
    setModelCompilationStatus = function(modelName, status) {
      return $("option[value=\"" + modelName + "\"]").addClass(status);
    };
    populateModelChoices = function(select, modelNames) {
      var modelName, option, _i, _len, _results;
      select.append($('<option>').text('Select a model...'));
      _results = [];
      for (_i = 0, _len = modelNames.length; _i < _len; _i++) {
        modelName = modelNames[_i];
        option = $('<option>').attr('value', modelName).text(modelName);
        _results.push(select.append(option));
      }
      return _results;
    };
    createModelSelection = function(container, modelNames) {
      var select;
      select = $('<select>').attr('name', 'models').css('width', '100%').addClass('chzn-select');
      select.on('change', function(e) {
        var modelURL;
        if (modelSelect.get(0).selectedIndex > 0) {
          modelURL = "" + (modelSelect.get(0).value) + ".nlogo";
          return selectionChanged(modelURL);
        }
      });
      populateModelChoices(select, modelNames);
      select.appendTo(container);
      select.chosen({
        search_contains: true
      });
      return select;
    };
    return $.ajax('/model/list.json', {
      complete: function(req, status) {
        var allModelNames;
        allModelNames = JSON.parse(req.responseText);
        window.modelSelect = createModelSelection(container, allModelNames);
        if (container[0].classList.contains('tortoise-model-list')) {
          return $.ajax('/model/statuses.json', {
            complete: function(req, status) {
              var allModelStatuses, modelName, modelStatus, _i, _len, _ref, _ref1;
              allModelStatuses = JSON.parse(req.responseText);
              for (_i = 0, _len = allModelNames.length; _i < _len; _i++) {
                modelName = allModelNames[_i];
                modelStatus = (_ref = (_ref1 = allModelStatuses[modelName]) != null ? _ref1.status : void 0) != null ? _ref : 'unknown';
                setModelCompilationStatus(modelName, modelStatus);
              }
              return window.modelSelect.trigger('chosen:updated');
            }
          });
        }
      }
    });
  };

  exports.modelList = function(container) {
    var uploadModel;
    uploadModel = function(modelURL) {
      return $.ajax("assets/modelslib/" + modelURL, {
        complete: function(req, status) {
          if (status === 'success') {
            return session.open(req.responseText);
          }
        }
      });
    };
    return exports.bindModelChooser(container, uploadModel);
  };

}).call(this);

//# sourceMappingURL=models.js.map
