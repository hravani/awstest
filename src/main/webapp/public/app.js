    var App = React.createClass({

        loadEmployeesFromServer: function () {
          var self = this;
          $.ajax({
            url: "http://localhost:5000/api/notes",
            method: 'GET',
            headers: {
                'Authorization': "Basic " + btoa("hiten:password")
            }
          }).then(function (data) {
            self.setState({notes: data});
          });
        },

        getInitialState: function () {
          return {notes: []};
        },

        componentDidMount: function () {
          this.loadEmployeesFromServer();
        },

        render() {
          return ( <NoteTable notes={this.state.notes}/> );
        }
    });

    var Note = React.createClass({
      getInitialState: function() {
        return {display: true };
      },
      handleDelete() {
        var self = this;
        $.ajax({
          url: "http://localhost:5000/api/notes/" + self.props.note.id,
          headers: {
              'Authorization': "Basic " + btoa("hiten:password")
          },
          type: 'DELETE',
          success: function(result) {
            toastr.success("Successfully Deleted");
            self.setState({display: false});
          },
          error: function(xhr, ajaxOptions, thrownError) {
            toastr.error(xhr.responseJSON.message);
          }
        });
      },
      render: function() {
        if(this.state.display == false) return null;
        else
        return (
          <tr key={'trkey' + this.props.note.id}>
            <td>{this.props.note.id}</td>
            <td>{this.props.note.title}</td>
            <td>{this.props.note.content}</td>
            <td>
              <button className="btn btn-info" onClick={this.handleDelete}>Delete</button>
            </td>
          </tr>
        );
      }
    });

    var NoteTable = React.createClass({
      render: function() {
        var rows = [];
        this.props.notes.forEach(function(note) {
          rows.push(<Note note={note} />);
        });
        return (
          <div className="container">
            <table className="table table-striped" key="tablekey">
              <thead>
                <tr key="statickey">
                  <th>Id</th><th>Title</th><th>Content</th><th>Delete</th>
                </tr>
              </thead>
              <tbody key="tbodykey">{rows}</tbody>
            </table>
          </div>
        );
      }
    });

    var NOTES = [
      {title: 'title-1', id: 1, content: 'content-1'},
      {title: 'title-2', id: 2, content: 'content-2'},
      {title: 'title-3', id: 3, content: 'content-3'},
      {title: 'title-4', id: 4, content: 'content-4'}
    ];

      ReactDOM.render(
        <App />, document.getElementById('root')
      );
